package com.iagolirapassos.dynamiccomponents;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;
import android.util.TypedValue;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.PagerAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.util.YailList;

import java.util.HashMap;
import java.util.Map;

import io.dynamiccomponents.helpers.*;

@DesignerComponent(
        version = 1,
        versionName = "1.0",
        description = "<p>Media components for dynamic components extension.</p>",
        nonVisible = true,
        iconName = "icon.png"
)
public class DynamicComponentsMedia extends DynamicComponentsBase {

    public DynamicComponentsMedia(ComponentContainer container) {
        super(container);
    }

    @SimpleFunction(description = "Create and add a dynamic CardView to a layout.")
    public void CreateDynamicCardView(
            final String cardTitle,
            final String cardText,
            final AndroidViewComponent layoutComponent,
            final int cardViewId,
            final int titleTextColor,
            final int textTextColor,
            final int cardBackgroundColor,
            final boolean isVisible,
            final int titleTextSize,
            final int textTextSize,
            final int setRadius,
            final String profileImagePath,
            final boolean profileImageVisible,
            final int cardWidth,
            final int cardHeight,
            final boolean widthFillParent,
            final boolean heightFillParent,
            final YailList marginList,
            final @Options(AlignmentText.class) int textAlignment,
            final @Options(AlignmentVertical.class) int verticalAlignment,
            final @Options(AlignmentHorizontal.class) int horizontalAlignment,
            final @Options(AlignmentHorizontal.class) int textImageProfileAlignment
    ) {
        android.os.Handler handler = new android.os.Handler(android.os.Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                MyCardViewComponent cardviewComponent = new MyCardViewComponent(container);
                CardView cardView = (CardView) cardviewComponent.getView();
                cardView.setCardBackgroundColor(cardBackgroundColor);
                cardView.setId(cardViewId);

                // Adicionar listeners de clique
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Click(cardViewId);
                    }
                });

                cardView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        DoubleClick(cardViewId);
                        return true;
                    }
                });

                LinearLayout mainLayout = new LinearLayout(layoutComponent.getView().getContext());
                mainLayout.setOrientation(LinearLayout.HORIZONTAL);
                mainLayout.setPadding(16, 16, 16, 16);

                switch (textImageProfileAlignment) {
                    case 0: mainLayout.setGravity(Gravity.LEFT); break;
                    case 1: mainLayout.setGravity(Gravity.CENTER_HORIZONTAL); break;
                    case 2: mainLayout.setGravity(Gravity.RIGHT); break;
                }

                final ImageView profileImageView = new ImageView(layoutComponent.getView().getContext());
                profileImageView.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
                profileImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    profileImageView.setClipToOutline(true);
                    profileImageView.setOutlineProvider(new ViewOutlineProvider() {
                        @Override
                        public void getOutline(View view, Outline outline) {
                            int diameter = Math.min(view.getWidth(), view.getHeight());
                            outline.setOval(0, 0, diameter, diameter);
                        }
                    });
                }

                if (profileImageVisible) {
                    loadImage(profileImagePath, profileImageView);
                    profileImageView.setVisibility(View.VISIBLE);
                } else {
                    profileImageView.setVisibility(View.GONE);
                }

                LinearLayout textLayout = new LinearLayout(layoutComponent.getView().getContext());
                textLayout.setOrientation(LinearLayout.VERTICAL);
                textLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, 
                    LinearLayout.LayoutParams.WRAP_CONTENT));

                TextView titleTextView = new TextView(layoutComponent.getView().getContext());
                titleTextView.setText(cardTitle);
                titleTextView.setTextColor(titleTextColor);
                titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleTextSize);
                textLayout.addView(titleTextView);

                TextView textTextView = new TextView(layoutComponent.getView().getContext());

                switch (textAlignment) {
                    case 0:
                        titleTextView.setGravity(Gravity.LEFT);
                        textTextView.setGravity(Gravity.LEFT);
                        break;
                    case 1:
                        titleTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                        textTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                        break;
                    case 2:
                        titleTextView.setGravity(Gravity.RIGHT);
                        textTextView.setGravity(Gravity.RIGHT);
                        break;
                }

                textTextView.setText(cardText);
                textTextView.setTextColor(textTextColor);
                textTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textTextSize);
                textLayout.addView(textTextView);

                if (textImageProfileAlignment == AlignmentHorizontal.Right.toUnderlyingValue()) {
                    mainLayout.addView(textLayout);
                    if (profileImageVisible) mainLayout.addView(profileImageView);
                } else {
                    if (profileImageVisible) mainLayout.addView(profileImageView);
                    mainLayout.addView(textLayout);
                }

                cardView.addView(mainLayout);

                int leftMargin = 16, topMargin = 16, rightMargin = 16, bottomMargin = 16;
                if (marginList != null && marginList.size() == 4) {
                    leftMargin = Integer.parseInt(marginList.getString(0));
                    topMargin = Integer.parseInt(marginList.getString(1));
                    rightMargin = Integer.parseInt(marginList.getString(2));
                    bottomMargin = Integer.parseInt(marginList.getString(3));
                }

                int finalCardWidth = widthFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : cardWidth;
                int finalCardHeight = heightFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : cardHeight;

                LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(finalCardWidth, finalCardHeight);
                cardParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

                switch (horizontalAlignment) {
                    case 0: cardParams.gravity = Gravity.LEFT; break;
                    case 1: cardParams.gravity = Gravity.CENTER_HORIZONTAL; break;
                    case 2: cardParams.gravity = Gravity.RIGHT; break;
                }

                switch (verticalAlignment) {
                    case 0: cardParams.gravity |= Gravity.TOP; break;
                    case 1: cardParams.gravity |= Gravity.CENTER_VERTICAL; break;
                    case 2: cardParams.gravity |= Gravity.BOTTOM; break;
                }

                cardView.setLayoutParams(cardParams);
                cardView.setRadius((float) setRadius);
                cardView.setVisibility(isVisible ? View.VISIBLE : View.GONE);

                LinearLayout mainLayoutContainer = getLayoutFromComponent(layoutComponent);
                if (mainLayoutContainer != null) {
                    mainLayoutContainer.addView(cardView);
                    dynamicComponents.put(cardViewId, cardviewComponent);
                    ComponentCreated("CardView", cardViewId);
                    Log.i("DynamicComponents", "CardView created with ID: " + cardViewId);
                }
            }
        });
    }

    @SimpleFunction(description = "Create and add a dynamic ImageView")
    public void CreateDynamicImage(
            final String imagePath,
            final AndroidViewComponent layoutComponent,
            final int imageId,
            final int width,
            final int height,
            final boolean widthFillParent,
            final boolean heightFillParent,
            final boolean isVisible
    ) {
        if (isURL(imagePath)) {
            loadImageFromUrl(imagePath, layoutComponent, imageId, width, height, widthFillParent, heightFillParent, isVisible);
        } else {
            loadImageFromFile(imagePath, layoutComponent, imageId, width, height, widthFillParent, heightFillParent, isVisible);
        }
    }

    @SimpleFunction(description = "Create a dynamic image slider.")
    public void CreateImageSlider(final int componentId, final List<String> imageUrls, 
            final AndroidViewComponent layoutComponent, final int viewPagerWidth, 
            final int viewPagerHeight, final int imageWidth, final int imageHeight) {
        
        android.os.Handler handler = new android.os.Handler(android.os.Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (layoutComponent != null && layoutComponent.getView() instanceof ViewGroup) {
                    ViewGroup layoutViewGroup = (ViewGroup) layoutComponent.getView();

                    MyViewPagerComponent viewPagerComponent = new MyViewPagerComponent(container, context);
                    viewPagerComponent.setAdapter(new ImageSliderAdapter(context, imageUrls, imageWidth, imageHeight));

                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        viewPagerWidth == -1 ? ViewGroup.LayoutParams.MATCH_PARENT : viewPagerWidth,
                        viewPagerHeight == -1 ? ViewGroup.LayoutParams.MATCH_PARENT : viewPagerHeight
                    );
                    viewPagerComponent.getView().setLayoutParams(params);
                    viewPagerComponent.setId(componentId);

                    layoutViewGroup.addView(viewPagerComponent.getView());
                    dynamicComponents.put(componentId, viewPagerComponent);
                    ComponentCreated("DynamicImageSlider", componentId);
                }
            }
        });
    }

    // Private helper methods
    private void loadImage(String imagePath, ImageView imageView) {
        if (isURL(imagePath)) {
            new DownloadImageTask(new DownloadImageTask.Callback() {
                @Override
                public void onImageDownloaded(Bitmap result) {
                    if (result != null) {
                        imageView.setImageBitmap(result);
                    }
                }
            }).execute(imagePath);
        } else {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private void loadImageFromUrl(final String imageUrl, final AndroidViewComponent layoutComponent,
            final int imageId, final int width, final int height,
            final boolean widthFillParent, final boolean heightFillParent, final boolean isVisible) {
        
        new DownloadImageTask(new DownloadImageTask.Callback() {
            @Override
            public void onImageDownloaded(Bitmap result) {
                if (result != null) {
                    android.os.Handler handler = new android.os.Handler(android.os.Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            createAndAddImageView(result, layoutComponent, imageId, width, height, 
                                                  widthFillParent, heightFillParent, isVisible);
                        }
                    });
                }
            }
        }).execute(imageUrl);
    }

    private void loadImageFromFile(String imagePath, AndroidViewComponent layoutComponent,
            int imageId, int width, int height, boolean widthFillParent, 
            boolean heightFillParent, boolean isVisible) {
        
        String assetsPath = getDynamicAssetsPath(context, imagePath);
        if (!doesImageExist(assetsPath)) {
            ReportError("Image file not found: " + imagePath);
            return;
        }

        try {
            FileInputStream input = new FileInputStream(new File(assetsPath));
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            input.close();
            
            if (bitmap != null) {
                createAndAddImageView(bitmap, layoutComponent, imageId, width, height, 
                                     widthFillParent, heightFillParent, isVisible);
            }
        } catch (IOException e) {
            ReportError("Could not load image: " + imagePath);
        }
    }

    private void createAndAddImageView(Bitmap bitmap, AndroidViewComponent layoutComponent,
            int imageId, int width, int height, boolean widthFillParent, 
            boolean heightFillParent, boolean isVisible) {
        
        MyImageComponent imageComponent = new MyImageComponent(container);
        ImageView imageView = (ImageView) imageComponent.getView();

        int layoutWidth = widthFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : width;
        int layoutHeight = heightFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : height;
        imageView.setLayoutParams(new LinearLayout.LayoutParams(layoutWidth, layoutHeight));
        imageView.setImageBitmap(bitmap);
        imageView.setVisibility(isVisible ? View.VISIBLE : View.GONE);

        LinearLayout layout = getLayoutFromComponent(layoutComponent);
        if (layout != null) {
            layout.addView(imageView);
            dynamicComponents.put(imageId, imageComponent);
            ComponentCreated("DynamicImage", imageId);
        }
    }

    // Image Slider Adapter
    private class ImageSliderAdapter extends PagerAdapter {
        private Context context;
        private List<String> imageUrls;
        private int imageWidth;
        private int imageHeight;

        public ImageSliderAdapter(Context context, List<String> imageUrls, int imageWidth, int imageHeight) {
            this.context = context;
            this.imageUrls = imageUrls;
            this.imageWidth = imageWidth;
            this.imageHeight = imageHeight;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            try {
                final ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    imageWidth == -1 ? ViewGroup.LayoutParams.MATCH_PARENT : imageWidth,
                    imageHeight == -1 ? ViewGroup.LayoutParams.MATCH_PARENT : imageHeight
                ));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                String imageUrlOrPath = imageUrls.get(position);

                if (isURL(imageUrlOrPath)) {
                    new DownloadImageTask(new DownloadImageTask.Callback() {
                        @Override
                        public void onImageDownloaded(Bitmap bitmap) {
                            if (bitmap != null) {
                                imageView.setImageBitmap(bitmap);
                            }
                        }
                    }).execute(imageUrlOrPath);
                } else {
                    String imagePath = getDynamicAssetsPath(context, imageUrlOrPath);
                    if (doesImageExist(imagePath)) {
                        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                        imageView.setImageBitmap(bitmap);
                    }
                }

                container.addView(imageView);
                return imageView;
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }

        @Override
        public int getCount() { return imageUrls.size(); }

        @Override
        public boolean isViewFromObject(View view, Object object) { return view == object; }
    }

    // DownloadImageTask
    private static class DownloadImageTask {
        private Callback callback;
        private static ExecutorService executor = Executors.newSingleThreadExecutor();

        public DownloadImageTask(Callback callback) { this.callback = callback; }

        public void execute(String... urls) {
            final String imageUrl = urls[0];
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    final Bitmap result = downloadBitmap(imageUrl);
                    if (callback != null) {
                        new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() { callback.onImageDownloaded(result); }
                        });
                    }
                }
            });
        }

        private Bitmap downloadBitmap(String imageUrl) {
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                return BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                return null;
            }
        }

        public interface Callback { void onImageDownloaded(Bitmap result); }
    }

    // Helper methods
    private boolean isURL(String input) {
        try {
            new URL(input);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    private boolean doesImageExist(String imagePath) {
        return new File(imagePath).exists();
    }

    private String getDynamicAssetsPath(Context context, String fileName) {
        String externalAssetsPath = context.getExternalFilesDir(null).getAbsolutePath();
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            return externalAssetsPath + "/assets/" + fileName;
        } else if (context.getPackageName().contains("makeroid")) {
            return "/storage/emulated/0/Kodular/assets/" + fileName;
        } else {
            return externalAssetsPath + "/AppInventor/assets/" + fileName;
        }
    }

    // Component Classes
    public class MyCardViewComponent extends AndroidViewComponent {
        private CardView cardview;
        public MyCardViewComponent(ComponentContainer container) {
            super(container);
            cardview = new CardView(container.$form().$context());
        }
        @Override
        public View getView() { return cardview; }
        public Context getContext() { return container.$form().$context(); }
    }

    private class MyImageComponent extends AndroidViewComponent {
        private ImageView imageView;
        public MyImageComponent(ComponentContainer container) {
            super(container);
            imageView = new ImageView(container.$form().$context());
        }
        @Override
        public View getView() { return imageView; }
    }

    public class MyViewPagerComponent extends AndroidViewComponent {
        private ViewPager viewPager;
        public MyViewPagerComponent(ComponentContainer container, Context context) {
            super(container);
            viewPager = new ViewPager(context);
        }
        @Override
        public View getView() { return viewPager; }
        public void setAdapter(PagerAdapter adapter) { viewPager.setAdapter(adapter); }
        public void setId(int id) { viewPager.setId(id); }
    }
}