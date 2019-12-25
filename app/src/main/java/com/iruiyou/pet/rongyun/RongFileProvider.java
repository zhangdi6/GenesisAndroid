package com.iruiyou.pet.rongyun;

/**
 * 处理第三方冲突问题：TakePhotoFragment调用相机与融云的冲突，路径冲突导致的，
 * 重新指向FileProvider，不要直接在清单文件中使用FileProvider
 */
public class RongFileProvider extends android.support.v4.content.FileProvider {
}
