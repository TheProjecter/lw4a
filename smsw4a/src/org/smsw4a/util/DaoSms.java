/**
 * 
 */
package org.smsw4a.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.smsw4a.domain.Sms;
import org.smsw4a.domain.Sms.SmsType;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * @author andres
 * 
 */
public class DaoSms implements IDaoSms {

	private Context context;

	/**
	 * 
	 */
	public DaoSms(Context context) {
		this.context = context;
	}

	/**
	 * Sms types: 1 inbox, 2 enviado, 3 en borrador, 4 outbox - no se ha enviado
	 * aun, 5 outgoing failed - no se enviara, 6 el outgoing no pudo ser
	 * enviado, se enviara mas tarde
	 */

	/**
	 * 
	 */
	@Override
	public List<Sms> getAllSms() {
		LogW.info(getClass(), "getting all sms...");
		List<Sms> smsList = new ArrayList<Sms>();

		final Uri from = Uri.parse("content://sms/");
		final String[] columns = null;// all columns
		final String conditions = null;// get all sms
		final String[] conditionsArguments = null;// no conditions, no arguments
		final String order = "date DESC"; // newest first

		Cursor cursor = context.getContentResolver().query(from, columns, conditions, conditionsArguments, order);
		LogW.info(getClass(), "count sms " + cursor.getCount());

		try {
			while (cursor.moveToNext()) {
				Sms sms = new Sms();
				Long id = cursor.getLong(cursor.getColumnIndex("_id"));
				sms.setId(id);
				String address = cursor.getString(cursor.getColumnIndex("address"));
				sms.setAddress(address);
				String body = cursor.getString(cursor.getColumnIndex("body"));
				sms.setBody(body);

				Long dateLong = cursor.getLong(cursor.getColumnIndex("date"));
				Calendar date = Calendar.getInstance();
				date.setTimeInMillis(dateLong);
				sms.setDate(date.getTime());

				int read = cursor.getInt(cursor.getColumnIndex("read"));
				if (read == 1) {
					sms.setRead(true);
				} else {
					sms.setRead(false);
				}

				int seen = cursor.getInt(cursor.getColumnIndex("seen"));
				if (seen == 1) {
					sms.setSeen(true);
				} else {
					sms.setSeen(false);
				}

				int type = cursor.getInt(cursor.getColumnIndex("type"));
				switch (type) {
				case 1:
					sms.setType(SmsType.INCOMING);
					break;
				case 2:
					sms.setType(SmsType.OUTGOING);
					break;
				case 3:
					sms.setType(SmsType.DRAFT);
					break;
				case 4:
					sms.setType(SmsType.OUTGOING_SENDING);
					break;
				case 5:
					sms.setType(SmsType.OUTGOING_FAILED);
					break;
				case 6:
					sms.setType(SmsType.OUTGOING_LATER);
					break;
				default:
					sms.setType(SmsType.UNKNOWN);
					break;
				}
				smsList.add(sms);
			}
		}catch(Exception e){
			LogW.error(getClass(), "An error occurred iterating over the sms. "+e.getMessage());
		}finally {
			cursor.close();
		}

		return smsList;
	}

}
