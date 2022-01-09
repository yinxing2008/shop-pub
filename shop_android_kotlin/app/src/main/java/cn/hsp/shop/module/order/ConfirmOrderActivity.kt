package cn.hsp.shop.module.order

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmActivity
import cn.hsp.shop.network.response.CartItem
import cn.hsp.shop.utils.Constants.EXTRA_KEY_CART_ITEMS
import cn.hsp.shop.utils.Constants.EXTRA_KEY_COST_SUM
import cn.hsp.shop.utils.JsonUtil
import cn.hsp.shop.utils.toast
import kotlinx.android.synthetic.main.activity_goods.*
import kotlinx.android.synthetic.main.order_bottom_layout.*
import kotlinx.android.synthetic.main.order_fee_layout.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class ConfirmOrderActivity : BaseVmActivity<ConfirmOrderViewModel>() {
    private var cartItemList: List<CartItem>? = null
    override fun viewModelClass() = ConfirmOrderViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_confirm_order

    override fun initView() {
        initToolbar()
    }

    override fun initData() {
        val cartItemsInJson = intent.getStringExtra(EXTRA_KEY_CART_ITEMS)
        cartItemList = JsonUtil.fromJsonList(cartItemsInJson!!)
        val sum = intent.getStringExtra(EXTRA_KEY_COST_SUM)
        goodsSumTv.text = sum
        sumTv.text = sum
    }

    override fun initListeners() {
        createOrderTv.setOnClickListener {
            cartItemList?.let { createOrder(it) }
        }
    }

    private fun createOrder(cartItemList: List<CartItem>) {
        mViewModel.createOrder(cartItemList, onSuccess = {
            showPayDialog(it)
        })
    }

    private fun showPayDialog(orderId: String) {
        val message = "现在支付么？"
        val alertDialog = AlertDialog.Builder(this).setMessage(message).setCancelable(false)
            .setPositiveButton(R.string.pay)
            { _, _ ->
                pay(orderId)
            }
            .setNegativeButton(R.string.cancel) { _, _ ->
                Toast.makeText(this, "用户点击了取消", Toast.LENGTH_SHORT).show()
            }
            .create()
        alertDialog.show()
    }

    private fun pay(orderId: String) {
        mViewModel.payForOrder(orderId, onSuccess = {
            toast("付款成功")
        })
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }
}
