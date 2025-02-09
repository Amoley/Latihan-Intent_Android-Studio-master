package com.dicoding.picodiploma.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult: TextView
    companion object {
        private const val REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveActivityDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        btnMoveActivityDataActivity.setOnClickListener(this)

        val btnMoveWithObject: Button = findViewById(R.id.btn_move_with_object)
        btnMoveWithObject.setOnClickListener(this)

        val btnDialPhone: Button =findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        val btnMoveForResult:Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)
        tvResult = findViewById(R.id.tv_result);
    }

    override fun onClick(v: View) {
       when (v.id){
           R.id.btn_move_activity ->{
               val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
               startActivity(moveIntent)
           }

           R.id.btn_move_activity_data -> {
            val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
            moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Muhammad Royan Amoley")
            moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 17)
            startActivity(moveWithDataIntent)
           }
           R.id.btn_move_with_object -> {
               val person = Person(
                   "Muhammad Royan Amoley",
                   17,
                   "3103199123@student.smktelkom-pwt.sch.id",
                   "Purwokerto"
               )
               val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
               moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
               startActivity(moveWithObjectIntent)
           }
           R.id.btn_dial_number -> {
               val phoneNumber = "085239194990"
               val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
               startActivity(dialPhoneIntent)
           }
           R.id.btn_move_for_result -> {
               val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
               startActivityForResult(moveForResultIntent, REQUEST_CODE)
           }
       }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveForResultActivity.RESULT_CODE) {
                val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
                tv_result.text = "Hasil : $selectedValue"
            }
        }
    }
}