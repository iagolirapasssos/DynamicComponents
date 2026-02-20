package com.iagolirapassos.dynamiccomponents;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.util.YailList;

import com.iagolirapassos.helpers.*;

@DesignerComponent(
        version = 1,
        versionName = "1.0",
        description = "<p>Beauty and texturing extension for dynamic components. Adds shadows, gradients, borders, animations and professional visual effects.</p>",
        nonVisible = true,
        iconName = "icon.png"
)
public class DynamicComponentsBeauty extends DynamicComponentsBase {

    public DynamicComponentsBeauty(ComponentContainer container) {
        super(container);
    }

    // ==================== SHADOW EFFECTS ====================

    @SimpleFunction(description = "Apply shadow to a component.")
    public void ApplyShadow(int componentId, int shadowColor, int shadowRadius, int shadowDx, int shadowDy) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            View view = component.getView();
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setElevation(shadowRadius);
                view.setTranslationZ(shadowRadius / 2f);
                
                view.setOutlineProvider(new ViewOutlineProvider() {
                    @Override
                    public void getOutline(View view, Outline outline) {
                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 8);
                    }
                });
                view.setClipToOutline(true);
            } else {
                createLegacyShadow(view, shadowColor, shadowRadius, shadowDx, shadowDy);
            }
        }
    }

    private void createLegacyShadow(View view, int shadowColor, int shadowRadius, int shadowDx, int shadowDy) {
        GradientDrawable shadowDrawable = new GradientDrawable();
        shadowDrawable.setColor(shadowColor);
        shadowDrawable.setAlpha(100);
        shadowDrawable.setCornerRadius(8);
        
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{shadowDrawable});
        view.setBackground(layerDrawable);
    }

    @SimpleFunction(description = "Apply neon glow effect to a component.")
    public void ApplyNeonGlow(int componentId, int glowColor, int glowRadius) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            View view = component.getView();
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setElevation(glowRadius * 2);
                view.setTranslationZ(glowRadius);
                
                view.setOutlineProvider(new ViewOutlineProvider() {
                    @Override
                    public void getOutline(View view, Outline outline) {
                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), glowRadius);
                    }
                });
                
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    view.setForeground(createNeonOverlay(glowColor, glowRadius));
                }
            }
        }
    }

    private Drawable createNeonOverlay(int color, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setAlpha(50);
        drawable.setCornerRadius(radius);
        return drawable;
    }

    // ==================== GRADIENT EFFECTS ====================

    @SimpleFunction(description = "Apply linear gradient background to a component.")
    public void ApplyLinearGradient(int componentId, int startColor, int endColor, 
                                    @Options(GradientDirection.class) int direction) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            View view = component.getView();
            
            GradientDrawable gradient = new GradientDrawable();
            
            switch (direction) {
                case 0: // TOP_BOTTOM
                    gradient.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                    break;
                case 1: // BOTTOM_TOP
                    gradient.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
                    break;
                case 2: // LEFT_RIGHT
                    gradient.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                    break;
                case 3: // RIGHT_LEFT
                    gradient.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
                    break;
                case 4: // TL_BR
                    gradient.setOrientation(GradientDrawable.Orientation.TL_BR);
                    break;
                case 5: // TR_BL
                    gradient.setOrientation(GradientDrawable.Orientation.TR_BL);
                    break;
            }
            
            gradient.setColors(new int[]{startColor, endColor});
            gradient.setCornerRadius(8);
            
            view.setBackground(gradient);
        }
    }

    @SimpleFunction(description = "Apply radial gradient background to a component.")
    public void ApplyRadialGradient(int componentId, int centerColor, int edgeColor, int gradientRadius) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            final View view = component.getView();
            
            view.post(new Runnable() {
                @Override
                public void run() {
                    int[] colors = {centerColor, edgeColor};
                    float[] positions = {0f, 1f};
                    
                    RadialGradient radialGradient = new RadialGradient(
                        view.getWidth() / 2f,
                        view.getHeight() / 2f,
                        gradientRadius,
                        colors,
                        positions,
                        Shader.TileMode.CLAMP
                    );
                    
                    PaintDrawable drawable = new PaintDrawable();
                    drawable.getPaint().setShader(radialGradient);
                    view.setBackground(drawable);
                }
            });
        }
    }

    // ==================== BORDER EFFECTS ====================

    @SimpleFunction(description = "Apply border to a component.")
    public void ApplyBorder(int componentId, int borderColor, int borderWidth, int cornerRadius) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            View view = component.getView();
            
            GradientDrawable drawable = new GradientDrawable();
            drawable.setStroke(borderWidth, borderColor);
            drawable.setCornerRadius(cornerRadius);
            
            Drawable currentBackground = view.getBackground();
            if (currentBackground instanceof ColorDrawable) {
                int color = ((ColorDrawable) currentBackground).getColor();
                drawable.setColor(color);
            }
            
            view.setBackground(drawable);
        }
    }

    @SimpleFunction(description = "Apply dashed border to a component.")
    public void ApplyDashedBorder(int componentId, int borderColor, int borderWidth, 
                                  int cornerRadius, int dashWidth, int dashGap) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            View view = component.getView();
            
            GradientDrawable drawable = new GradientDrawable();
            drawable.setStroke(borderWidth, borderColor, dashWidth, dashGap);
            drawable.setCornerRadius(cornerRadius);
            
            view.setBackground(drawable);
        }
    }

    // ==================== ANIMATION EFFECTS ====================

    @SimpleFunction(description = "Apply fade animation to a component.")
    public void AnimateFade(final int componentId, int duration, float startAlpha, float endAlpha) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            final View view = component.getView();
            
            view.setAlpha(startAlpha);
            view.animate()
                .alpha(endAlpha)
                .setDuration(duration)
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        fireAnimationStarted(componentId, "Fade");
                    }
                })
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        fireAnimationEnded(componentId, "Fade");
                    }
                })
                .start();
        }
    }

    @SimpleFunction(description = "Apply scale animation to a component.")
    public void AnimateScale(final int componentId, int duration, float scaleX, float scaleY) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            final View view = component.getView();
            
            view.animate()
                .scaleX(scaleX)
                .scaleY(scaleY)
                .setDuration(duration)
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        fireAnimationStarted(componentId, "Scale");
                    }
                })
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        fireAnimationEnded(componentId, "Scale");
                    }
                })
                .start();
        }
    }

    @SimpleFunction(description = "Apply rotation animation to a component.")
    public void AnimateRotation(final int componentId, int duration, float degrees) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            final View view = component.getView();
            
            view.animate()
                .rotation(degrees)
                .setDuration(duration)
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        fireAnimationStarted(componentId, "Rotation");
                    }
                })
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        fireAnimationEnded(componentId, "Rotation");
                    }
                })
                .start();
        }
    }

    @SimpleFunction(description = "Apply bounce animation to a component.")
    public void AnimateBounce(final int componentId, int duration) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            final View view = component.getView();
            
            view.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(duration / 3)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        view.animate()
                            .scaleX(0.9f)
                            .scaleY(0.9f)
                            .setDuration(duration / 3)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    view.animate()
                                        .scaleX(1f)
                                        .scaleY(1f)
                                        .setDuration(duration / 3)
                                        .withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                fireAnimationEnded(componentId, "Bounce");
                                            }
                                        })
                                        .start();
                                }
                            })
                            .start();
                    }
                })
                .start();
            
            fireAnimationStarted(componentId, "Bounce");
        }
    }

    // ==================== TEXTURE EFFECTS ====================

    @SimpleFunction(description = "Apply noise texture to a component.")
    public void ApplyNoiseTexture(int componentId, int noiseColor, int intensity) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            View view = component.getView();
            
            Bitmap noiseBitmap = createNoiseTexture(100, 100, noiseColor, intensity);
            BitmapDrawable noiseDrawable = new BitmapDrawable(context.getResources(), noiseBitmap);
            noiseDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            
            Drawable currentBackground = view.getBackground();
            if (currentBackground != null) {
                LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{currentBackground, noiseDrawable});
                view.setBackground(layerDrawable);
            } else {
                view.setBackground(noiseDrawable);
            }
        }
    }

    private Bitmap createNoiseTexture(int width, int height, int color, int intensity) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        android.graphics.Canvas canvas = new android.graphics.Canvas(bitmap);
        
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int variation = (int) (Math.random() * intensity);
                int newRed = Math.min(255, Math.max(0, red + variation - intensity/2));
                int newGreen = Math.min(255, Math.max(0, green + variation - intensity/2));
                int newBlue = Math.min(255, Math.max(0, blue + variation - intensity/2));
                
                int noisePixel = Color.argb(255, newRed, newGreen, newBlue);
                bitmap.setPixel(x, y, noisePixel);
            }
        }
        
        return bitmap;
    }

    @SimpleFunction(description = "Apply gradient overlay to a component.")
    public void ApplyGradientOverlay(int componentId, int startColor, int endColor, float overlayAlpha) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            View view = component.getView();
            
            GradientDrawable overlay = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{startColor, endColor}
            );
            overlay.setAlpha((int) (overlayAlpha * 255));
            
            Drawable currentBackground = view.getBackground();
            if (currentBackground != null) {
                LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{currentBackground, overlay});
                view.setBackground(layerDrawable);
            } else {
                view.setBackground(overlay);
            }
        }
    }

    // ==================== BLUR EFFECTS ====================

    @SimpleFunction(description = "Apply blur effect to a component.")
    public void ApplyBlurEffect(final int componentId, int blurRadius) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            final View view = component.getView();
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                view.setRenderEffect(android.graphics.RenderEffect.createBlurEffect(
                    blurRadius, blurRadius, android.graphics.Shader.TileMode.CLAMP
                ));
            } else {
                view.setAlpha(0.95f);
                fireReportError("Blur effect requires Android 12+ for full support");
            }
        }
    }

    // ==================== TRANSFORMATION EFFECTS ====================

    @SimpleFunction(description = "Transform a component into a circle.")
    public void MakeCircular(int componentId) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            View view = component.getView();
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setOutlineProvider(new ViewOutlineProvider() {
                    @Override
                    public void getOutline(View view, Outline outline) {
                        outline.setOval(0, 0, view.getWidth(), view.getHeight());
                    }
                });
                view.setClipToOutline(true);
            } else {
                GradientDrawable drawable = new GradientDrawable();
                drawable.setShape(GradientDrawable.OVAL);
                
                Drawable currentBackground = view.getBackground();
                if (currentBackground instanceof ColorDrawable) {
                    drawable.setColor(((ColorDrawable) currentBackground).getColor());
                }
                
                view.setBackground(drawable);
            }
        }
    }

    @SimpleFunction(description = "Make component a perfect circle with border.")
    public void MakeCircularWithBorder(int componentId, int borderColor, int borderWidth) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            View view = component.getView();
            
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.OVAL);
            drawable.setStroke(borderWidth, borderColor);
            
            Drawable currentBackground = view.getBackground();
            if (currentBackground instanceof ColorDrawable) {
                drawable.setColor(((ColorDrawable) currentBackground).getColor());
            }
            
            view.setBackground(drawable);
        }
    }

    // ==================== UTILITY METHODS ====================

    @SimpleFunction(description = "Reset all visual effects on a component.")
    public void ResetEffects(int componentId) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            View view = component.getView();
            
            view.setAlpha(1f);
            view.setScaleX(1f);
            view.setScaleY(1f);
            view.setRotation(0f);
            view.setTranslationX(0f);
            view.setTranslationY(0f);
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setElevation(0f);
                view.setTranslationZ(0f);
                view.setOutlineProvider(null);
            }
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                view.setRenderEffect(null);
            }
        }
    }

    @SimpleFunction(description = "Combine multiple beauty effects into a style.")
    public void ApplyStylePreset(int componentId, @Options(StylePreset.class) int stylePreset) {
        switch (stylePreset) {
            case 0: // MATERIAL
                ApplyShadow(componentId, Color.BLACK, 10, 0, 4);
                ApplyBorder(componentId, Color.LTGRAY, 1, 4);
                break;
                
            case 1: // NEON
                ApplyNeonGlow(componentId, Color.CYAN, 15);
                ApplyBorder(componentId, Color.CYAN, 2, 8);
                break;
                
            case 2: // ELEGANT
                ApplyLinearGradient(componentId, Color.DKGRAY, Color.BLACK, 0);
                ApplyBorder(componentId, Color.GRAY, 2, 0);
                break;
                
            case 3: // FUN
                ApplyRadialGradient(componentId, Color.YELLOW, Color.RED, 50);
                AnimateBounce(componentId, 1000);
                break;
                
            case 4: // MINIMAL
                ApplyBorder(componentId, Color.LTGRAY, 1, 0);
                break;
                
            case 5: // GLOSSY
                ApplyGradientOverlay(componentId, Color.WHITE, Color.TRANSPARENT, 0.3f);
                ApplyShadow(componentId, Color.BLACK, 8, 0, 2);
                break;
        }
    }

    @SimpleFunction(description = "Apply continuous bounce animation.")
    public void ApplyBounceAnimation(final int componentId, final int duration) {
        final AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            final View view = component.getView();
            
            view.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(duration / 4)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        view.animate()
                            .scaleX(0.8f)
                            .scaleY(0.8f)
                            .setDuration(duration / 2)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    view.animate()
                                        .scaleX(1f)
                                        .scaleY(1f)
                                        .setDuration(duration / 4)
                                        .withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                ApplyBounceAnimation(componentId, duration);
                                            }
                                        })
                                        .start();
                                }
                            })
                            .start();
                    }
                })
                .start();
            
            fireAnimationStarted(componentId, "ContinuousBounce");
        }
    }

    // ==================== EVENTS ====================

    @SimpleEvent(description = "Event triggered when an animation starts.")
    public void AnimationStarted(int componentId, String animationType) {
        EventDispatcher.dispatchEvent(this, "AnimationStarted", componentId, animationType);
    }

    @SimpleEvent(description = "Event triggered when an animation ends.")
    public void AnimationEnded(int componentId, String animationType) {
        EventDispatcher.dispatchEvent(this, "AnimationEnded", componentId, animationType);
    }

    protected void fireAnimationStarted(int componentId, String animationType) {
        AnimationStarted(componentId, animationType);
    }

    protected void fireAnimationEnded(int componentId, String animationType) {
        AnimationEnded(componentId, animationType);
    }
}