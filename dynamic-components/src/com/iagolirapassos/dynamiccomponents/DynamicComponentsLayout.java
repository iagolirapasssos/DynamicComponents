package com.iagolirapassos.dynamiccomponents;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.util.Log;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;

import java.util.HashMap;
import java.util.Map;

import io.dynamiccomponents.helpers.*;

@DesignerComponent(
        version = 1,
        versionName = "1.0",
        description = "<p>Layout components for dynamic components extension.</p>",
        nonVisible = true,
        iconName = "icon.png"
)
public class DynamicComponentsLayout extends DynamicComponentsBase {

    private final Map<Integer, MyHorizontalArrangementComponent> horizontalArrangements = new HashMap<>();
    private final Map<Integer, MyVerticalArrangementComponent> verticalArrangements = new HashMap<>();

    public DynamicComponentsLayout(ComponentContainer container) {
        super(container);
    }

    @SimpleFunction(description = "Create and add a dynamic Space to a layout.")
    public void CreateDynamicSpace(
            AndroidViewComponent layoutComponent,
            final int spaceId,
            int width,
            int height,
            boolean widthFillParent,
            boolean heightFillParent
    ) {
        MySpaceComponent spaceComponent = new MySpaceComponent(container);
        Space space = (Space) spaceComponent.getView();

        int layoutWidth = widthFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : width;
        int layoutHeight = heightFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : height;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(layoutWidth, layoutHeight);
        space.setLayoutParams(params);
        space.setId(spaceId);

        LinearLayout layout = getLayoutFromComponent(layoutComponent);
        if (layout != null) {
            layout.addView(space);
            dynamicComponents.put(spaceId, spaceComponent);
            ComponentCreated("Space", spaceId);
            Log.i("DynamicComponents", "Space created with ID: " + spaceId);
        }
    }

    @SimpleFunction(description = "Create and add a dynamic Vertical Arrangement to a layout.")
    public void CreateDynamicVerticalArrangement(
            final AndroidViewComponent layoutComponent,
            final int verticalArrangementId,
            final boolean isVisible,
            final int width,
            final int height,
            final boolean widthFillParent,
            final boolean heightFillParent,
            final int backgroundColor,
            final @Options(AlignmentHorizontal.class) int horizontalAlignment,
            final @Options(AlignmentVertical.class) int verticalAlignment
    ) {
        android.os.Handler handler = new android.os.Handler(android.os.Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                MyVerticalArrangementComponent verticalComponent = new MyVerticalArrangementComponent(container);
                LinearLayout verticalLayout = (LinearLayout) verticalComponent.getView();

                int layoutWidth = widthFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : width;
                int layoutHeight = heightFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : height;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(layoutWidth, layoutHeight);

                switch (horizontalAlignment) {
                    case 0: params.gravity = Gravity.LEFT; break;
                    case 1: params.gravity = Gravity.CENTER_HORIZONTAL; break;
                    case 2: params.gravity = Gravity.RIGHT; break;
                }
                verticalLayout.setLayoutParams(params);
                verticalLayout.setBackgroundColor(backgroundColor);
                verticalLayout.setId(verticalArrangementId);
                verticalLayout.setVisibility(isVisible ? View.VISIBLE : View.GONE);

                switch (verticalAlignment) {
                    case 0: verticalLayout.setGravity(Gravity.TOP); break;
                    case 1: verticalLayout.setGravity(Gravity.CENTER_VERTICAL); break;
                    case 2: verticalLayout.setGravity(Gravity.BOTTOM); break;
                }

                verticalLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Click(verticalArrangementId);
                    }
                });

                verticalLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        DoubleClick(verticalArrangementId);
                        return true;
                    }
                });

                LinearLayout mainLayout = getLayoutFromComponent(layoutComponent);
                if (mainLayout != null) {
                    mainLayout.addView(verticalLayout);
                    dynamicComponents.put(verticalArrangementId, verticalComponent);
                    verticalArrangements.put(verticalArrangementId, verticalComponent);
                    ComponentCreated("VerticalArrangement", verticalArrangementId);
                    Log.i("DynamicComponents", "Vertical Arrangement created with ID: " + verticalArrangementId);
                }
            }
        });
    }

    @SimpleFunction(description = "Create and add a dynamic Horizontal Arrangement to a layout.")
    public void CreateDynamicHorizontalArrangement(
            final AndroidViewComponent layoutComponent,
            final int horizontalArrangementId,
            final boolean isVisible,
            final int width,
            final int height,
            final boolean widthFillParent,
            final boolean heightFillParent,
            final int backgroundColor,
            final @Options(AlignmentHorizontal.class) int horizontalAlignment,
            final @Options(AlignmentVertical.class) int verticalAlignment
    ) {
        android.os.Handler handler = new android.os.Handler(android.os.Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                MyHorizontalArrangementComponent horizontalComponent = new MyHorizontalArrangementComponent(container);
                LinearLayout horizontalLayout = (LinearLayout) horizontalComponent.getView();

                int layoutWidth = widthFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : width;
                int layoutHeight = heightFillParent ? LinearLayout.LayoutParams.MATCH_PARENT : height;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(layoutWidth, layoutHeight);

                switch (horizontalAlignment) {
                    case 0: params.gravity = Gravity.LEFT; break;
                    case 1: params.gravity = Gravity.CENTER_HORIZONTAL; break;
                    case 2: params.gravity = Gravity.RIGHT; break;
                }
                horizontalLayout.setLayoutParams(params);
                horizontalLayout.setBackgroundColor(backgroundColor);
                horizontalLayout.setId(horizontalArrangementId);
                horizontalLayout.setVisibility(isVisible ? View.VISIBLE : View.GONE);

                int gravity = 0;
                switch (verticalAlignment) {
                    case 0: gravity |= Gravity.TOP; break;
                    case 1: gravity |= Gravity.CENTER_VERTICAL; break;
                    case 2: gravity |= Gravity.BOTTOM; break;
                }
                horizontalLayout.setGravity(gravity);

                horizontalLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Click(horizontalArrangementId);
                    }
                });

                horizontalLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        DoubleClick(horizontalArrangementId);
                        return true;
                    }
                });

                LinearLayout mainLayout = getLayoutFromComponent(layoutComponent);
                if (mainLayout != null) {
                    mainLayout.addView(horizontalLayout);
                    dynamicComponents.put(horizontalArrangementId, horizontalComponent);
                    horizontalArrangements.put(horizontalArrangementId, horizontalComponent);
                    ComponentCreated("HorizontalArrangement", horizontalArrangementId);
                    Log.i("DynamicComponents", "Horizontal Arrangement created with ID: " + horizontalArrangementId);
                }
            }
        });
    }

    // Component Classes
    public class MyVerticalArrangementComponent extends AndroidViewComponent {
        private LinearLayout verticalLayout;
        public MyVerticalArrangementComponent(ComponentContainer container) {
            super(container);
            verticalLayout = new LinearLayout(container.$form().$context());
            verticalLayout.setOrientation(LinearLayout.VERTICAL);
        }
        @Override
        public View getView() { return verticalLayout; }
    }

    public class MyHorizontalArrangementComponent extends AndroidViewComponent {
        private LinearLayout horizontalLayout;
        public MyHorizontalArrangementComponent(ComponentContainer container) {
            super(container);
            horizontalLayout = new LinearLayout(container.$form().$context());
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
        }
        @Override
        public View getView() { return horizontalLayout; }
    }

    public class MySpaceComponent extends AndroidViewComponent {
        private Space space;
        public MySpaceComponent(ComponentContainer container) {
            super(container);
            space = new Space(container.$form().$context());
        }
        @Override
        public View getView() { return space; }
        public Context getContext() { return container.$form().$context(); }
    }
}