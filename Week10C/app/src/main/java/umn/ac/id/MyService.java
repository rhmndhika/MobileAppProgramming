package umn.ac.id;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.i("CUSTOM SERVICE","onBind: Service Bind");
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("CUSTOMSERVICE","onCreate: CustomService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("CUSTOMSERVICE","onStartCommand: "+startId);
//        int n =(int)(Math.random()*50)+10;
//        try {
//            for(int i = 0; i < n; i++) {
//                Thread.sleep(200);
//                Log.i("CUSTOMSERVICE", "onStartCommand: " + startId +
//                        " berjalan "+ ((int)((100 * i)/(float) n)) + " persen");
//            }
//            Log.i("CUSTOMSERVICE", "onStartCommand: " + startId +
//                    " Selesai");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        AsyncTask customServiceTask = new CustomServiceTask()
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, startId);
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i("CUSTOMSERVICE","onDestroy: Service Destroyed");
    }

    public class CustomServiceTask extends AsyncTask<Integer, Integer,
                Integer> {
        @Override
        protected void onProgressUpdate(Integer ... integers){
            Log.i("CUSTOMSERVICE", "onStartCommand: " + integers[0] +
                    " berjalan "+ integers[1]+ " persen");
        }
        @Override
        protected void onPostExecute(Integer result ){
            Log.i("CUSTOMSERVICE", "onStartCommand: " + result+ " Selesai");
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            int startId = integers[0];
            int n =(int)(Math.random()*50)+10;
            try {
                for(int i = 0; i < n; i++) {
                    Thread.sleep(200);
                    publishProgress(startId, ((int)((100 * i)/(float) n)));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return startId;
        }
    }



}