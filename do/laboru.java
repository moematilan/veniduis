AssetManager assetManager = getAssets();
InputStream inputStream = assetManager.open("image.jpg");
Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
