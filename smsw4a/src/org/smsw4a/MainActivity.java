package org.smsw4a;

import java.util.List;

import org.smsw4a.domain.Sms;
import org.smsw4a.util.DaoSms;
import org.smsw4a.util.LogW;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DaoSms daoSms = new DaoSms(getBaseContext());
        List<Sms> smsList = daoSms.getAllSms();
        for (Sms sms : smsList) {
			LogW.info(getClass(), sms.getId()+" "+sms.getType()+" "+sms.getDate() +sms.getAddress()+" "+sms.getBody());
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
