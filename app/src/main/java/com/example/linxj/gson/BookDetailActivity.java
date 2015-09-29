package com.example.linxj.gson;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

;

import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Model.BookInfo;
import Net.NetAssistant;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by linxj on 2015/9/19.
 */
public class BookDetailActivity extends AppCompatActivity {

    @Bind(R.id.book_title)
    TextView bookTitle;
    @Bind(R.id.book_pic)
    ImageView bookPic;
    @Bind(R.id.book_Author)
    TextView bookAuthor;
    @Bind(R.id.book_abstract)
    TextView bookAbstract;
    String url;
    View view;
    @Bind(R.id.txv)
    LinearLayout txv;


    @Bind(R.id.scrollView)
    ScrollView scrollView;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            if (msg.obj.toString().equals(" ")) {
                Toast.makeText(BookDetailActivity.this, "无信息", Toast.LENGTH_SHORT).show();


            } else {
                txv.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.VISIBLE);
                BookInfo info = new MyJsonParse().parseInfoBook(msg.obj.toString());
                bookTitle.setText(info.getTitle().getTitle());
                bookAuthor.setText(info.getAuthor().get(0).getName().getAuthorName());
                bookAbstract.setText(info.getContent().getContent());
                Picasso.with(BookDetailActivity.this).load(info.getLink().get(2).getLhref()).placeholder(R.drawable.book_head).error(R.drawable.book_head).into(bookPic);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        txv.setVisibility(View.GONE);
        scrollView.setVisibility(View.GONE);
        url = "http://api.douban.com/book/subject/isbn/9787115346438?alt=json";
        ExecutorService executor = Executors.newCachedThreadPool();

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    String s = new NetAssistant(url).getBookInfoFromNet();
                    Log.e("info", s);
                    Message message = handler.obtainMessage();
                    message.obj = s;
                    message.sendToTarget();


                }
            });
        }
    }



