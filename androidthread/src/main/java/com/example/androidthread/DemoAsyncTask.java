package com.example.androidthread;

import android.os.AsyncTask;

/**
 * AsyncTask<Void,Integer,Boolean>
 *
 * 参数1：Params，执行AsyncTask时需要传入的参数，可用于在后台任务中使用
 *          这里指定为void，表示在执行AsyncTask的时候不需要传入参数给后台任务
 *
 * 参数2：Progress，后台任务执行时，如果需要在界面上显示当前的进度，则使用这里指定的泛型作为进度单位
 *          这里指定为Integer，表示使用整型数据来显示进度条的单位
 *
 * 参数3：Result，当任务执行完毕后，如果需要对结果进行返回，则使用这里指定的泛型作为返回值类型
 *          这里指定为Boolean，则表示使用Boolean型数据来反馈执行结果
 */
public class DemoAsyncTask extends AsyncTask<Void, Integer, Boolean> {


    /**
     * 这个方法会在后台任务开始执行之前调用，用于进行一些界面上的初始化操作，比如显示一个进度条对话框等
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 这个方法中所有的代码都会在子线程中运行，应该在这里去处理所有的耗时任务
     * 任务一旦完成就可以通过return语句来将任务的执行结果返回
     * 注意：这个方法中不可以进行UI操作，如果需要更新UI元素，比如说反馈当前任务的进度，可以调用publishProgress()方法来完成
     * @param voids
     * @return
     */
    @Override
    protected Boolean doInBackground(Void... voids) {
        return null;
    }

    /**
     * 当在后台任务中调用了publishProgress方法后，onProgressUpdate方法很快就会被调用，该方法携带的参数就是在后台任务中传递过来的。
     * 在这个方法中可以对UI进行操作，利用参数中的数值就可以对界面元素进行相应的更新
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    /**
     * 当后台任务执行完毕并通过return语句进行返回时，这个方法很快会被调用。返回的数据会作为参数传递到此方法中
     * 可以利用返回的数据进行一下UI操作，比如说提醒任务执行的结果，以及关闭进度条和对话框等
     * @param aBoolean
     */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}