package com.smdigital.doltinuku.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smdigital.doltinuku.R
import com.smdigital.doltinuku.activity.DetailActivity
import com.smdigital.doltinuku.adapter.FeedAdapter
import com.smdigital.doltinuku.model.ProductModel
import kotlinx.android.synthetic.main.fragment_feed.view.*

class FeedFragment : Fragment() {

    private var recyFedd: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        recyFedd = view.rvFeed

        recyFedd?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyFedd?.hasFixedSize()

        itemFeed()
        return view
    }

    private fun itemFeed() {
        val allItemFeed = ArrayList<ProductModel>()
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/7/23/309438718/309438718_902599f6-b1fe-4bf9-b24a-0f5b7d50835a_2048_2730.jpg", "https://images.pexels.com/users/avatars/312601/dam-t-ng-quan-741.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "The Beatles We Can Work It Out", "Rp 135.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/4/11/523375/523375_b094050a-eb39-423b-9e72-a29e986107e2.jpg", "https://images.pexels.com/users/avatars/54196/madison-inouye-175.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Nanoblock Sagrada Familia", "Rp 224.925"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/5/24/162320663/162320663_984b9322-afa2-4ab9-acd9-949442d01343_955_1011.jpg", "https://images.pexels.com/users/avatars/358536/d-van-trung-422.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Bubuk buah zuriat 100gram", "Rp 60.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/8/8/19750993/19750993_242cfe41-81a1-4d46-9361-768b9caba8ab_1000_667.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Happy Banana Monkey Couple Figurine ", "Rp 89.900"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/7/25/20248783/20248783_108a8f9f-6993-4eab-be20-4c2757579b89_1000_1000.jpg", "https://images.pexels.com/users/avatars/312601/dam-t-ng-quan-741.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Alibi Paris Notte Bag - T4611R1", "Rp 83.100"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/11/22/224247891/224247891_ceefffc6-3f99-4ac9-baef-37bce42e91b0_2048_0.jpg", "https://images.pexels.com/users/avatars/54196/madison-inouye-175.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Pulpen Gel Kokoro Zebra Colours", "Rp 3.800"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/5/15/7566550/7566550_62a1f6f9-8775-45d2-804f-f9b68b8d16a5_960_1280.jpeg", "https://images.pexels.com/users/avatars/54196/madison-inouye-175.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "TOBOT ATHLON 2 MINI ROCKY", "Rp 41.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/200-square/product-1/2017/8/30/171076084/171076084_6355c6a9-5674-4079-86e1-eece6a64259d_1400_933.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Handuk Merah Putih 30x70", "Rp 10.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/8/31/20168457/20168457_5ac90750-fa33-4dd4-ac98-aef50f080ceb_459_405.png", "https://images.pexels.com/users/avatars/358536/d-van-trung-422.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Foligain 5% Minoxidil Extra Strength for Men", "Rp 450.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/4/15/2034422/2034422_bc2bcc82-d096-4b48-8100-df7680d464e8.jpg", "https://images.pexels.com/users/avatars/312601/dam-t-ng-quan-741.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Gamis syari balotelli polos / basic dress - Navy, L", "Rp 90.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2015/7/2/156794/156794_2424d230-203f-11e5-aba5-c38549bc7260.jpg", "https://images.pexels.com/users/avatars/571159/fox-254.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "HELM RETRO BOGO KULIT CLASIC COKLAT KREM", "Rp 135.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2015/4/6/297009/297009_ff7f749c-dc75-11e4-9017-fec24908a8c2.jpg", "https://images.pexels.com/users/avatars/54196/madison-inouye-175.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Boneka Baymax White 35cm", "Rp 57.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/10/21/217841769/217841769_78e6648a-60ec-4a90-8308-aa1c039ea5e3_683_600.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Xiaomi Mi A1 4/64GB - Android One", "Rp 2.420.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/9/26/0/0_f894961d-0856-4d29-bcd8-f713029fd58a_680_443.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "ASUS E203NAH,N3350, 2GB,500GB 11,3\"", "Rp 3.300.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/4/25/31661069/31661069_faf4b477-d2ae-4b4c-849f-4cfd5b54914e_870_852.jpg", "https://images.pexels.com/users/avatars/358536/d-van-trung-422.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Cracked Heel cream krim untuk kaki/tumit pecah-pecah", "Rp 27.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/4/26/3263085/3263085_0300ba27-3ee5-48ad-a10b-36d9ee8f8387_1024_1024.jpg", "https://images.pexels.com/users/avatars/571159/fox-254.jpeg?w=60&h=60&fit=crop&crop=faces3", "John doe", "XPANDER SPOILER WARNA", "Rp 800.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/8/21/11412309/11412309_4b438402-e730-4560-bc45-97c074c6955f_1000_1431.jpg", "https://images.pexels.com/users/avatars/358536/d-van-trung-422.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Clean&Clear Cover & Correct BB cream 50ml", "Rp 130.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/4/14/15734237/15734237_5a0c256a-9bdd-44e3-ba50-ec4a59df2f56_700_622.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Flash Drive Toshiba 16GB - Putih", "Rp 35.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/9/28/21153453/21153453_4596e241-c4ad-4633-ae42-c3957ea14464_600_500.jpg", "https://images.pexels.com/users/avatars/312601/dam-t-ng-quan-741.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Piyama Anak Perempuan Tsum Tsum Picnic", "Rp 83.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/9/20/20484439/20484439_58f73ff3-0e56-43b3-a2a0-10088b594937_720_720.png", "https://images.pexels.com/users/avatars/571159/fox-254.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Lampu Depan LED Tyto M2A Philips", "Rp 55.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2016/8/14/9908230/9908230_205fcee7-b9c3-4a5d-8368-b51ab9c850aa.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Pisau Set Vicenza Original Warna Hitam", "Rp 122.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache///ecs7.tokopedia.net/img/cache/700/product-1/2017/11/5/9583069/9583069_1bd9432d-c3c0-4523-82cf-0e73bbc3e459_700_700.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "JBL Flip 3 Splashproof Portable Bluetooth ", "Rp 1.055.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/5/8/7933738/7933738_e59f3f9c-41aa-4d25-9350-107edee972ed_1498_1498.jpg", "https://images.pexels.com/users/avatars/312601/dam-t-ng-quan-741.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "POWELL BLACK | FORIND x Navara", "Rp 260.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/3/18/14934123/14934123_33b0351c-c611-418f-9b7c-9850edeadc62_300_315.jpg", "https://images.pexels.com/users/avatars/571159/fox-254.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "COMPOUND RUBBING ALFAGLOSS", "Rp 21.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/5/17/96230209/96230209_2e7f2fee-c2f3-4799-a769-94e43f8e376b_640_640.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "karpet bulu rasfur isi busa", "Rp 100.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2017/10/29/0/0_ea2b2404-f19b-4808-9d24-5d51aeb80bd6_540_540.jpg", "https://images.pexels.com/users/avatars/54196/madison-inouye-175.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Lego Keychain - Sponge Bob", "Rp 100.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/7/11/1349173/1349173_ab3062ed-ad1a-44a1-980b-0e1652cf9e5b_850_850.jpg", "https://images.pexels.com/users/avatars/135125/irina-kostenich-733.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Handuk Merah Putih 30x70", "Rp 10.000"))
        allItemFeed.add(ProductModel("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/1/10/7546897/7546897_24bbb41c-aa0e-4bea-9b95-85ed7cd19a8f_2048_0.jpg", "https://images.pexels.com/users/avatars/358536/d-van-trung-422.jpeg?w=60&h=60&fit=crop&crop=faces", "John doe", "Ekstrak Albumin Ikan Gabus", "Rp 100.000"))
        recyFedd?.adapter = FeedAdapter(allItemFeed) { partItem: ProductModel -> partItemClicked(partItem) }
    }

    private fun partItemClicked(partItem: ProductModel) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("JUDUL", partItem.titleProduct)
        intent.putExtra("HARGA", partItem.priceProduct)
        intent.putExtra("COVER", partItem.imageCover)
        intent.putExtra("PROFILE", partItem.imageProfile)
        intent.putExtra("USERNAME", partItem.userName)
        startActivity(intent)
        (context as Activity).overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back)
    }
}
