package com.example.room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.room.database.AppDatabase;
import com.example.room.database.BookmarkEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getAppDatabase(this);

        insertBookmark();
        updateBookmark(2);
        getAllBookmark();
        findBookmark(1);

    }

    private void insertBookmark() {
        BookmarkEntity bm = new BookmarkEntity();
        bm.title = "This is title";
        bm.content = "This is content";
        db.bookmarkDao().insertBookmark(bm);
    }
    private void updateBookmark(int id) {
        BookmarkEntity bm = db.bookmarkDao().getBookmark(id);
        bm.title = "This is title update";
        db.bookmarkDao().updateBookmark(bm);
    }
    private void findBookmark(int id) {
        BookmarkEntity model = db.bookmarkDao().getBookmark(id);
        Log.d("TAG", "Find Bookmark with id:"+model.id + "title: "+model.title);
    }
    private void deleteBookmark(int id) {
        BookmarkEntity model = db.bookmarkDao().getBookmark(id);
        db.bookmarkDao().deleteBookmark(model);
    }
    private void deleteAllBookmark() {
        db.bookmarkDao().deleteAll();
    }
    private void getAllBookmark() {
        List<BookmarkEntity> list = db.bookmarkDao().getAllBookmark();
        for (BookmarkEntity model : list) {
            Log.d("TAG", "id: "+model.id + "title: "+model.title);
        }
    }
}