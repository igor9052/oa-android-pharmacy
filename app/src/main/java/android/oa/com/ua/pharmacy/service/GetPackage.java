package android.oa.com.ua.pharmacy.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Igor Kuzmenko on 10.03.2015.
 * This service downloads content archive file from Internet
 */
public class GetPackage extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GetPackage(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
