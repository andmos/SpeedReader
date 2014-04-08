package com.throwit.speedreader;

import android.content.Context;

/**
 * Created by andreasmosti on 07.04.14.
 */
public class TextHandler {

       private IFileBasedTextService m_textSource;
       private Context m_context;
       private String m_fileName;

        public TextHandler(IFileBasedTextService textSource, Context context, String fileName){
                m_textSource = textSource;
                m_context = context;
                m_fileName = fileName;
        }

        public String[] getWordsFromFileText(){
            String[] wordList = m_textSource.getTextFromSource(m_context, m_fileName).split(" ");
            return wordList;
        }

}
