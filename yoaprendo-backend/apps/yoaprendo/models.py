from django.db import models
from yoaprendo import strings

class Item(models.Model):
    _id = models.CharField(strings.ID, max_length=128)
    title = models.CharField(strings.TITLE, max_length=128)
    resume = models.CharField(strings.RESUME, max_length=128)
    image = models.ImageField(strings.IMAGE_FILE,blank=False,
            upload_to='images/', null=True)
    sound = models.FileField(strings.SOUND_FILE,blank=False, null=True,
            upload_to='sounds/')

    def __unicode__(self):
        return self.title
