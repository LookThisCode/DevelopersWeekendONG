from django.utils import simplejson
from yoaprendo.models import Item
from django.http import HttpResponse
from django.shortcuts import get_object_or_404


def json_item(request, item_id):
    item = get_object_or_404(Item, pk=item_id)
    dict = {}
    dict['pk'] = item.pk
    dict['id'] = item._id
    dict['titulo_imagen'] = item.title
    dict['resumen_imagen'] = item.resume
    dict['imagen_url'] = item.image.url
    dict['sonido_url'] = item.sound.url
    return HttpResponse(simplejson.dumps(dict))


def view_all(request):
    items = Item.objects.all()
    dict = []
    for item in items:
        dict_item = {}
        dict_item['pk'] = item.id
        dict_item['id'] = item._id
        dict_item['titulo_imagen'] = item.title
        dict_item['resumen_imagen'] = item.resume
        dict_item['imagen_url'] = item.image.url
        dict_item['sonido_url'] = item.sound.url
        dict.append(dict_item)
    return HttpResponse(simplejson.dumps(dict))


