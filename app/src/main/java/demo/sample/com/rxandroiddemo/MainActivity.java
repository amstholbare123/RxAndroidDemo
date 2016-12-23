package demo.sample.com.rxandroiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    Observable<String> myObservable
            = Observable.just("Hello"); // Emits "Hello"


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observer<String> myObserver = new Observer<String>() {
            @Override
            public void onCompleted() {
                // Called when the observable has no more data to emit
            }

            @Override
            public void onError(Throwable e) {
                // Called when the observable encounters an error
            }

            @Override
            public void onNext(String s) {
                // Called each time the observable emits data
                Log.d("MY OBSERVER", s);
            }
        };
        Subscription mySubscription = myObservable.subscribe(myObserver); // executiom start point

        Observable<Integer> myArrayObservable
                = Observable.from(new Integer[]{1, 2, 3, 4, 5, 6}); // Emits each item of the array, one at a time

        myArrayObservable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer i) {
                Log.d("My Action", String.valueOf(i)); // Prints the number received
            }
        });

    myArrayObservable = myArrayObservable.map(new Func1<Integer, Integer>() { // Input and Output are both Integer
        @Override
        public Integer call(Integer integer) {
            return integer * integer; // Square the number
        }
    });
}}
