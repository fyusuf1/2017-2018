#SY Dec 2018
#github.com/magicake

from PIL import Image
import numpy as np
from random import randint
from timeit import default_timer as timer

def rgb_to_hsv(rgb):
    rgb = rgb.astype('float')
    hsv = np.zeros_like(rgb)
    hsv[..., 3:] = rgb[..., 3:]
    r, g, b = rgb[..., 0], rgb[..., 1], rgb[..., 2]
    maxc = np.max(rgb[..., :3], axis=-1)
    minc = np.min(rgb[..., :3], axis=-1)
    hsv[..., 2] = maxc
    mask = maxc != minc
    hsv[mask, 1] = (maxc - minc)[mask] / maxc[mask]
    rc = np.zeros_like(r)
    gc = np.zeros_like(g)
    bc = np.zeros_like(b)
    rc[mask] = (maxc - r)[mask] / (maxc - minc)[mask]
    gc[mask] = (maxc - g)[mask] / (maxc - minc)[mask]
    bc[mask] = (maxc - b)[mask] / (maxc - minc)[mask]
    hsv[..., 0] = np.select(
        [r == maxc, g == maxc], [bc - gc, 2.0 + rc - bc], default=4.0 + gc - rc)
    hsv[..., 0] = (hsv[..., 0] / 6.0) % 1.0
    return hsv

def hsv_to_rgb(hsv):
    rgb = np.empty_like(hsv)
    rgb[..., 3:] = hsv[..., 3:]
    h, s, v = hsv[..., 0], hsv[..., 1], hsv[..., 2]
    i = (h * 6.0).astype('uint8')
    f = (h * 6.0) - i
    p = v * (1.0 - s)
    q = v * (1.0 - s * f)
    t = v * (1.0 - s * (1.0 - f))
    i = i % 6
    conditions = [s == 0.0, i == 1, i == 2, i == 3, i == 4, i == 5]
    rgb[..., 0] = np.select(conditions, [v, q, p, p, t, v], default=v)
    rgb[..., 1] = np.select(conditions, [v, v, v, q, p, p], default=t)
    rgb[..., 2] = np.select(conditions, [v, p, t, v, v, q], default=p)
    return rgb.astype('uint8')


def shift_thing(arr,hout,thing, random):
    hsv=rgb_to_hsv(arr)
    if random:
        hsv[..., 0]=(180-(randint(0, 180)))/360.0
        hsv[..., 1]=(180-(randint(0, 180)))/360.0
        hsv[..., 1]=(180-(randint(0, 10)))/360.0
    else:
        hsv[...,thing]=hout
    rgb=hsv_to_rgb(hsv)
    return rgb

demo = int(raw_input("which demo? (2 is the star dude)"))
r_inq = int(raw_input("random? 1 for yes 2 for no"))
random = False
thing = 0
if r_inq == 1:
    random = True
else:
    thing = int(raw_input("1: hue, 2: saturation, 3: value")) - 1
iter = int(raw_input("how many iterations??"))
og = Image.open('demo'+str(demo)+'/og.png').convert('RGBA')
arr = np.array(og)
base = Image.open('demo'+str(demo)+'/base.png').convert('RGBA')
if __name__=='__main__':
    #deviation = int(raw_input("pick a number between 0 and 180"))
    #modified_hue = (180-deviation)/360.0

    #new_img = Image.fromarray(shift_hue(arr,modified_hue), 'RGBA')
    start = timer()
    for i in range (0, iter):
        deviation = int(randint(0, 180))
        modifier = (180-deviation)/360.0
        new_img = Image.fromarray(shift_thing(arr,modifier, thing, random), 'RGBA')
        result = Image.alpha_composite(base, new_img)
        result.save('opt_test/result'+str(i)+'.png')
        print(timer() - start)
    end = timer()
    print("Done: " + str(end-start))
