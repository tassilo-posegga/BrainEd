package eu.posegga.brained.home.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.subscribeOnIoObserveOnMain(): Single<T> =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun ImageView.load(url: String) =
    Glide.with(context).load(url).into(this)

fun <T> MutableList<T>.pop(): T? =
    if (this.count() > 0) this.removeAt(this.count() - 1) else null
