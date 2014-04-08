package com.throwit.speedreader;

import android.content.Context;

/**
 * Created by andreasmosti on 07.04.14.
 */
public interface IFileBasedTextService {

        public String getTextFromSource(Context context, String fileName);
}
