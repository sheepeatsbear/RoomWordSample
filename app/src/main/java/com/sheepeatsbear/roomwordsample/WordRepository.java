package com.sheepeatsbear.roomwordsample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

class WordRepository {

    private WordDao mWordDao;

    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {

        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);

        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    void insert(Word word) {

        new insertAsyncTask(mWordDao).execute(word);

    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {

            mAsyncTaskDao.insert(words[0]);
            return null;

        }
    }
}
