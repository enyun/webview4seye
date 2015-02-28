package com.imooc.android_webview;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private String url = "http://d1.sina.com.cn/litong/zhitou/sinaads/demo/SinaEyeFeedPage/index.html";
	private WebView webView;
    private ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web);
		// Uri uri = Uri.parse(url); //urlδ??δ??θ??ι“?ζ??η??ε?°ε??
		// Intent intent =new Intent(Intent.ACTION_VIEW, uri);
		// startActivity(intent);
		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		webView = (WebView) findViewById(R.id.webView);
		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient(){
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
		});
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webView.setWebChromeClient(new WebChromeClient(){
			
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				if(newProgress==100)
				{
					closeDialog();
				}
				else
				{
					openDialog(newProgress);
				}
			}

			private void closeDialog() {
				// TODO Auto-generated method stub
                  if(dialog!=null&&dialog.isShowing())
                  {
                	     dialog.dismiss();
                	     dialog=null;
                  }
			}

			private void openDialog(int newProgress) {
				if(dialog==null)
				{
					dialog=new ProgressDialog(MainActivity.this);
					dialog.setTitle("Loading Page from Sina!");
					dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					dialog.setProgress(newProgress);
					dialog.show();
					
				}
				else
				{
					dialog.setProgress(newProgress);
				}
			
				
			}
		});
		
		
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			if(webView.canGoBack())
			{
				webView.goBack();
				return true;
			}
			else
			{
				System.exit(0);
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
}


