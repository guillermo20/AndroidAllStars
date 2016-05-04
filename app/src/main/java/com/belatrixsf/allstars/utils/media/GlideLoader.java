/* The MIT License (MIT)
* Copyright (c) 2016 BELATRIX
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:

* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.

* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

package com.belatrixsf.allstars.utils.media;

import android.content.Context;
import android.widget.ImageView;

import com.belatrixsf.allstars.ui.common.views.BorderedCircleTransformation;
import com.belatrixsf.allstars.ui.common.views.CircleTransformation;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

import java.io.File;

/**
 * @author Carlos Piñan
 */
public class GlideLoader implements ImageLoader {

    @Override
    public void loadFromUrl(String url, ImageView imageView, ImageTransformation transformation) {
        Context context = imageView.getContext();
        load(context, Glide.with(context).load(url), transformation).into(imageView);
    }

    @Override
    public void loadFromPath(String path, ImageView imageView, ImageTransformation transformation) {
        Context context = imageView.getContext();
        load(context, Glide.with(context).load(new File(path)), transformation).into(imageView);
    }

    private <T> DrawableTypeRequest<T> load(Context context, DrawableTypeRequest<T> load, ImageTransformation transformation) {
        load.fitCenter();
        if (context != null && transformation != null) {
            switch (transformation) {
                case BORDERED_CIRCLE:
                    load.transform(new BorderedCircleTransformation(context));
                    break;
                case CIRCLE:
                    load.transform(new CircleTransformation(context));
                    break;
            }
        }
        return load;
    }
}
