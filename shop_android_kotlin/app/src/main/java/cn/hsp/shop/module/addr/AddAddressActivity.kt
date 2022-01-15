package cn.hsp.shop.module.addr

import android.content.Intent
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmActivity
import cn.hsp.shop.module.order.ConfirmOrderActivity
import cn.hsp.shop.network.response.UserAddr
import cn.hsp.shop.utils.Constants
import cn.hsp.shop.utils.JsonUtil
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.activity_goods.toolbar
import kotlinx.android.synthetic.main.part_addr_detail.*
import kotlinx.android.synthetic.main.part_addr_name_phone.*
import kotlinx.android.synthetic.main.part_addr_type_info.*


/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class AddAddressActivity : BaseVmActivity<AddressViewModel>() {
    override fun layoutResId(): Int = R.layout.activity_add_address
    override fun viewModelClass() = AddressViewModel::class.java
    override fun initView() {
        initToolbar()
    }

    override fun initListeners() {
        modifyAddrTv.setOnClickListener {
            val userAddr = UserAddr()
            userAddr.name = receiverNameEt.text.toString()
            userAddr.phone = receiverPhoneEt.text.toString()
            userAddr.region = regionEt.text.toString()
            userAddr.address = detailedAddrEt.text.toString()
            userAddr.defaultAddress = defaultAddrSwitch.isChecked
            mViewModel.addAddress(userAddr, onSuccess = {
                sendResultForConfirmOrder(it)
                finish()
            })
        }
    }

    /**
     * 确定订单页面需要返回数据
     */
    private fun sendResultForConfirmOrder(it: UserAddr) {
        val intent = Intent()
        intent.putExtra(Constants.EXTRA_KEY_USER_ADDR, JsonUtil.toJson(it))
        setResult(ConfirmOrderActivity.requestCodeForSelectAddr, intent)
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

}
