package com.iagolirapassos.dynamiccomponents;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.FrameLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.Html;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.util.Log;

import androidx.cardview.widget.CardView;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.util.YailList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.iagolirapassos.helpers.*;

@DesignerComponent(
        version = 1,
        versionName = "1.0",
        description = "<p>Core components for dynamic components extension.</p>",
        nonVisible = true,
        iconName = "icon.png"
)
public class DynamicComponentsCore extends DynamicComponentsBase {

    public DynamicComponentsCore(ComponentContainer container) {
        super(container);
    }

    @SimpleFunction(description = "Create and add a dynamic button to a layout.")
    public void CreateDynamicButton(
            String text,
            AndroidViewComponent layoutComponent,
            final int buttonId,
            int textColor,
            boolean isBold,
            boolean isItalic,
            boolean isVisible,
            int textSize,
            int backgroundColor,
            int width,
            int height,
            boolean widthFillParent,
            boolean heightFillParent
    ) {
        MyButtonComponent buttonComponent = new MyButtonComponent(container);
        Button button = (Button) buttonComponent.getView();
        button.setText(text);
        button.setTextColor(textColor);

        int layoutWidth = widthFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : width;
        int layoutHeight = heightFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : height;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(layoutWidth, layoutHeight);
        params.gravity = Gravity.CENTER;
        button.setLayoutParams(params);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        button.setBackgroundColor(backgroundColor);
        button.setId(buttonId);

        if (isBold && isItalic) {
            button.setTypeface(null, Typeface.BOLD_ITALIC);
        } else if (isBold) {
            button.setTypeface(null, Typeface.BOLD);
        } else if (isItalic) {
            button.setTypeface(null, Typeface.ITALIC);
        }

        button.setVisibility(isVisible ? View.VISIBLE : View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireClick(buttonId);
            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                fireDoubleClick(buttonId);
                return true;
            }
        });

        LinearLayout layout = getLayoutFromComponent(layoutComponent);
        if (layout != null) {
            layout.addView(button);
            registry.registerComponent(buttonId, buttonComponent);
            fireComponentCreated("Button", buttonId);
            Log.i("DynamicComponents", "Button created with ID: " + buttonId);
        }
    }

    @SimpleFunction(description = "Create and add a dynamic label to a layout.")
    public void CreateDynamicLabel(
            final String text,
            final AndroidViewComponent layoutComponent,
            final int labelId,
            final boolean isBold,
            final boolean isItalic,
            final int textColor,
            final int width,
            final int height,
            final boolean widthFillParent,
            final boolean heightFillParent,
            final boolean isVisible,
            final int textSize,
            final int backgroundColor,
            final boolean acceptsHtml,
            final @Options(AlignmentHorizontal.class) int horizontalAlignment,
            final @Options(AlignmentVertical.class) int verticalAlignment,
            final @Options(AlignmentText.class) int horizontalAlignmentText,
            final @Options(AlignmentVertical.class) int verticalAlignmentText
    ) {
        android.os.Handler handler = new android.os.Handler(android.os.Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                MyLabelComponent labelComponent = new MyLabelComponent(container);
                TextView label = (TextView) labelComponent.getView();

                label.setText(text);
                label.setTextColor(textColor);

                int layoutWidth = widthFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : width;
                int layoutHeight = heightFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : height;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(layoutWidth, layoutHeight);

                switch (horizontalAlignment) {
                    case 0: params.gravity = Gravity.LEFT; break;
                    case 1: params.gravity = Gravity.CENTER_HORIZONTAL; break;
                    case 2: params.gravity = Gravity.RIGHT; break;
                }
                label.setLayoutParams(params);
                label.setBackgroundColor(backgroundColor);
                label.setId(labelId);

                if (isBold && isItalic) {
                    label.setTypeface(null, Typeface.BOLD_ITALIC);
                } else if (isBold) {
                    label.setTypeface(null, Typeface.BOLD);
                } else if (isItalic) {
                    label.setTypeface(null, Typeface.ITALIC);
                }

                label.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

                int gravity = 0;
                switch (horizontalAlignmentText) {
                    case 0: gravity |= Gravity.LEFT; break;
                    case 1: gravity |= Gravity.CENTER_HORIZONTAL; break;
                    case 2: gravity |= Gravity.RIGHT; break;
                }
                switch (verticalAlignmentText) {
                    case 0: gravity |= Gravity.TOP; break;
                    case 1: gravity |= Gravity.CENTER_VERTICAL; break;
                    case 2: gravity |= Gravity.BOTTOM; break;
                }
                label.setGravity(gravity);

                label.setVisibility(isVisible ? View.VISIBLE : View.GONE);

                label.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fireClick(labelId);
                    }
                });

                label.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        fireDoubleClick(labelId);
                        return true;
                    }
                });

                if (acceptsHtml) {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        label.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
                    } else {
                        @SuppressWarnings("deprecation")
                        CharSequence htmlText = Html.fromHtml(text);
                        label.setText(htmlText);
                    }
                }

                LinearLayout layout = getLayoutFromComponent(layoutComponent);
                if (layout != null) {
                    layout.addView(label);
                    registry.registerComponent(labelId, labelComponent);
                    fireComponentCreated("Label", labelId);
                    Log.i("DynamicComponents", "Label created with ID: " + labelId);
                }
            }
        });
    }

    @SimpleFunction(description = "Create and add a dynamic TextBox to a layout.")
    public void CreateDynamicTextBox(
            final String hint,
            final AndroidViewComponent layoutComponent,
            final int textBoxId,
            final int width,
            final int height,
            final boolean widthFillParent,
            final boolean heightFillParent,
            final boolean isMultiline,
            final int hintColor,
            final int textColor,
            final boolean isBold,
            final boolean isItalic,
            final boolean isVisible,
            final int textSize,
            final int backgroundColor,
            final @Options(AlignmentHorizontal.class) int horizontalAlignment,
            final @Options(AlignmentText.class) int textAlignment
    ) {
        android.os.Handler handler = new android.os.Handler(android.os.Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                MyTextBoxComponent textBoxComponent = new MyTextBoxComponent(container);
                final EditText textBox = (EditText) textBoxComponent.getView();

                textBox.setHint(hint);
                textBox.setTextColor(textColor);
                textBox.setHintTextColor(hintColor);

                int layoutWidth = widthFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : width;
                int layoutHeight = heightFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : height;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(layoutWidth, layoutHeight);

                switch (horizontalAlignment) {
                    case 0: params.gravity = Gravity.LEFT; break;
                    case 1: params.gravity = Gravity.CENTER_HORIZONTAL; break;
                    case 2: params.gravity = Gravity.RIGHT; break;
                }
                textBox.setLayoutParams(params);
                textBox.setBackgroundColor(backgroundColor);
                textBox.setId(textBoxId);

                switch (textAlignment) {
                    case 0: textBox.setGravity(Gravity.LEFT); break;
                    case 1: textBox.setGravity(Gravity.CENTER); break;
                    case 2: textBox.setGravity(Gravity.RIGHT); break;
                }

                if (isBold && isItalic) {
                    textBox.setTypeface(null, Typeface.BOLD_ITALIC);
                } else if (isBold) {
                    textBox.setTypeface(null, Typeface.BOLD);
                } else if (isItalic) {
                    textBox.setTypeface(null, Typeface.ITALIC);
                }

                textBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
                textBox.setVisibility(isVisible ? View.VISIBLE : View.GONE);

                textBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fireClick(textBoxId);
                    }
                });

                textBox.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}
                    @Override
                    public void afterTextChanged(Editable s) {
                        fireTextChanged(textBoxId, s.toString());
                    }
                });

                LinearLayout layout = getLayoutFromComponent(layoutComponent);
                if (layout != null) {
                    layout.addView(textBox);
                    registry.registerComponent(textBoxId, textBoxComponent);
                    fireComponentCreated("TextBox", textBoxId);
                    Log.i("DynamicComponents", "TextBox created with ID: " + textBoxId);
                }
            }
        });
    }

    @SimpleFunction(description = "Get the current text or hint of a dynamic TextBox.")
    public String GetDynamicTextBoxText(int textBoxId, boolean isHint) {
        AndroidViewComponent component = registry.getComponent(textBoxId);
        if (component != null && component instanceof MyTextBoxComponent) {
            EditText textBox = (EditText) component.getView();
            if (textBox != null) {
                return isHint ? textBox.getHint().toString() : textBox.getText().toString();
            }
        }
        return null;
    }

    @SimpleFunction(description = "Get a dynamic component and its layout information by ID.")
    public YailList GetDynamicComponentWithLayout(int componentId) {
        AndroidViewComponent component = registry.getComponent(componentId);
        
        if (component != null && component.getView() != null) {
            View componentView = component.getView();
            String layoutName = "unknown";
            AndroidViewComponent layoutComponent = null;
            
            ViewParent parent = componentView.getParent();
            if (parent instanceof ViewGroup) {
                ViewGroup parentViewGroup = (ViewGroup) parent;
                
                if (parentViewGroup instanceof LinearLayout) {
                    int orientation = ((LinearLayout) parentViewGroup).getOrientation();
                    layoutName = (orientation == LinearLayout.VERTICAL) ? "VerticalArrangement" : "HorizontalArrangement";
                } else if (parentViewGroup instanceof FrameLayout) {
                    layoutName = "FrameLayout";
                } else if (parentViewGroup instanceof CardView) {
                    layoutName = "CardView";
                }
                
                for (Map.Entry<Integer, AndroidViewComponent> entry : registry.getAllComponents().entrySet()) {
                    if (entry.getValue().getView() == parentViewGroup) {
                        layoutComponent = entry.getValue();
                        break;
                    }
                }
            }
            
            List<Object> infoList = new ArrayList<>();
            infoList.add(component);
            infoList.add(layoutName);
            infoList.add(layoutComponent);
            infoList.add(componentId);
            
            return YailList.makeList(infoList);
        }
        
        return YailList.makeList(new ArrayList<>());
    }

    @SimpleFunction(description = "Get the layout component that contains a dynamic component.")
    public AndroidViewComponent GetParentLayoutOfComponent(int componentId) {
        AndroidViewComponent component = registry.getComponent(componentId);
        
        if (component != null && component.getView() != null) {
            View componentView = component.getView();
            ViewParent parent = componentView.getParent();
            
            if (parent instanceof ViewGroup) {
                ViewGroup parentViewGroup = (ViewGroup) parent;
                
                for (Map.Entry<Integer, AndroidViewComponent> entry : registry.getAllComponents().entrySet()) {
                    if (entry.getValue().getView() == parentViewGroup) {
                        return entry.getValue();
                    }
                }
            }
        }
        
        return null;
    }

    @SimpleFunction(description = "Get the layout type of a dynamic component.")
    public String GetComponentLayoutType(int componentId) {
        AndroidViewComponent component = registry.getComponent(componentId);
        
        if (component != null && component.getView() != null) {
            View componentView = component.getView();
            ViewParent parent = componentView.getParent();
            
            if (parent instanceof LinearLayout) {
                int orientation = ((LinearLayout) parent).getOrientation();
                return (orientation == LinearLayout.VERTICAL) ? "VerticalArrangement" : "HorizontalArrangement";
            } else if (parent instanceof FrameLayout) {
                return "FrameLayout";
            } else if (parent instanceof CardView) {
                return "CardView";
            }
        }
        
        return "unknown";
    }

    @SimpleFunction(description = "Check if a dynamic component exists in a specific layout.")
    public boolean IsComponentInLayout(int componentId, int layoutId) {
        AndroidViewComponent component = registry.getComponent(componentId);
        AndroidViewComponent layout = registry.getComponent(layoutId);
        
        if (component != null && component.getView() != null && layout != null && layout.getView() != null) {
            View componentView = component.getView();
            ViewGroup layoutView = (ViewGroup) layout.getView();
            
            return componentView.getParent() == layoutView;
        }
        
        return false;
    }

    @SimpleFunction(description = "Get all child components of a layout component.")
    public YailList GetChildComponentsOfLayout(int layoutId) {
        AndroidViewComponent layout = registry.getComponent(layoutId);
        List<Object> childList = new ArrayList<>();
        
        if (layout != null && layout.getView() instanceof ViewGroup) {
            ViewGroup layoutView = (ViewGroup) layout.getView();
            
            for (Map.Entry<Integer, AndroidViewComponent> entry : registry.getAllComponents().entrySet()) {
                int componentId = entry.getKey();
                AndroidViewComponent component = entry.getValue();
                
                if (component != null && component.getView() != null) {
                    if (component.getView().getParent() == layoutView) {
                        childList.add(componentId);
                    }
                }
            }
        }
        
        return YailList.makeList(childList);
    }

    @SimpleFunction(description = "Get the component ID from a view.")
    public int GetComponentIdFromView(AndroidViewComponent component) {
        if (component != null && component.getView() != null) {
            for (Map.Entry<Integer, AndroidViewComponent> entry : registry.getAllComponents().entrySet()) {
                if (entry.getValue() == component) {
                    return entry.getKey();
                }
            }
        }
        return -1;
    }

    @SimpleEvent(description = "Event triggered when text in the TextBox changes.")
    public void TextChangedEvent(int textBoxId, String newText) {
        EventDispatcher.dispatchEvent(this, "TextChangedEvent", textBoxId, newText);
    }

    private class MyButtonComponent extends AndroidViewComponent {
        private Button button;
        public MyButtonComponent(ComponentContainer container) {
            super(container);
            button = new Button(container.$form().$context());
        }
        @Override
        public View getView() { return button; }
    }

    public class MyLabelComponent extends AndroidViewComponent {
        private TextView label;
        public MyLabelComponent(ComponentContainer container) {
            super(container);
            label = new TextView(container.$form().$context());
        }
        @Override
        public View getView() { return label; }
    }

    public class MyTextBoxComponent extends AndroidViewComponent {
        private EditText textbox;
        public MyTextBoxComponent(ComponentContainer container) {
            super(container);
            textbox = new EditText(container.$form().$context());
        }
        @Override
        public View getView() { return textbox; }
        public Context getContext() { return container.$form().$context(); }
    }
}