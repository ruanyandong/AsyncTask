# AsyncTask

/**
 * AsyncTask注意事项：
 * 1、必须在UI线程中创建AsyncTask的实例
 * 2、必须在UI线程中调用AsyncTask的execute()方法
 * 3、重写的四个方法是系统自动调用的，不应手动调用
 * 4、每个AsyncTask只能被执行一次，多次调用将引发异常
 */
