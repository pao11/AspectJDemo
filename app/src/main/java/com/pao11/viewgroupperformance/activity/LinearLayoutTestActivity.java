
package com.pao11.viewgroupperformance.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.pao11.viewgroupperformance.R;

/**
 *
 */
public class LinearLayoutTestActivity extends Activity {

  private LinearLayout myLinearLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_linear_layout_test);

    myLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutOne);
    myLinearLayout.invalidate();
  }
}
