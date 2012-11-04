# -*- coding: utf-8 -*-

from django.conf.urls.defaults import patterns, include, url
from yoaprendo import views


urlpatterns = patterns('',
        url(r'^(?P<item_id>[-\w]+)/details.json$',
            views.json_item,
            name='json_item',
        ),
        url(r'^data.json$',
            views.view_all,
            name='view_all',
        ),
)
