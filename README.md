# 🧩 DynamicComponents

<div align="center">
  <h3><kbd>📦 com.iagolirapassos.dynamiccomponents</kbd></h3>
  <p><strong>Extension for creating dynamic components associated with a layout in MIT App Inventor 2</strong></p>
  
  <p>
    <img src="https://img.shields.io/badge/Version-1.2-brightgreen" alt="Version 1.2"/>
    <img src="https://img.shields.io/badge/Size-66.45%20KB-blue" alt="Size 66.45 KB"/>
    <img src="https://img.shields.io/badge/Min%20API-7-orange" alt="Minimum API Level 7"/>
    <img src="https://img.shields.io/badge/Updated-2025--05--24-yellow" alt="Updated 2025-05-24"/>
  </p>
</div>

## 📋 Overview

**DynamicComponents** is a powerful extension that allows you to create and manage UI components dynamically at runtime. Create buttons, labels, text boxes, cards, images, sliders, and layouts programmatically with full control over their properties and behavior.

## 🛠️ Specifications

| Property | Value |
|----------|-------|
| **Package** | `com.iagolirapassos.dynamiccomponents` |
| **Size** | 66.45 KB |
| **Version** | 1.2 |
| **Minimum API Level** | 7 |
| **Updated On** | 2025-05-24 |
| **Built with** | [FAST CLI v3.3.1](https://community.appinventor.mit.edu/t/fast-an-efficient-way-to-build-extensions/129103?u=jewel) |

## 📚 Table of Contents

- [Events](#-events)
- [Methods](#-methods)
  - [Core Components](#core-components)
  - [Layout Components](#layout-components)
  - [Media Components](#media-components)
  - [Properties & Utilities](#properties--utilities)
- [Designer Properties](#-designer-properties)
- [Helper Enums](#-helper-enums)

---

## 🎯 Events

The extension provides 5 events to handle user interactions and component lifecycle.

### Click
Triggered when a dynamic component is clicked.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | ID of the clicked component |

### DoubleClick
Triggered when a dynamic component is double-clicked.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | ID of the double-clicked component |

### ComponentCreated
Triggered when a dynamic component is successfully created.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentName` | `text` | Name/type of the component created |
| `componentID` | `number` | ID assigned to the component |

### TextChangedEvent
Triggered when text in a dynamic TextBox changes.

| Parameter | Type | Description |
|-----------|------|-------------|
| `textBoxId` | `number` | ID of the TextBox |
| `newText` | `text` | Current text content |

### ReportError
Reports an error with a custom message.

| Parameter | Type | Description |
|-----------|------|-------------|
| `errorMessage` | `text` | Error description |

---

## ⚙️ Methods

The extension includes **25 methods** organized by category.

### Core Components

#### GenerateUniqueId
Generates a unique ID for dynamic components.

```
Returns: number
```

#### CreateDynamicButton
Creates and adds a dynamic button to a layout.

| Parameter | Type | Description |
|-----------|------|-------------|
| `text` | `text` | Button text |
| `layoutComponent` | `component` | Parent layout |
| `buttonId` | `number` | Unique ID |
| `textColor` | `number` | Text color |
| `isBold` | `boolean` | Bold style |
| `isItalic` | `boolean` | Italic style |
| `isVisible` | `boolean` | Visibility |
| `textSize` | `number` | Text size (sp) |
| `backgroundColor` | `number` | Background color |
| `width` | `number` | Width in pixels |
| `height` | `number` | Height in pixels |
| `widthFillParent` | `boolean` | Match parent width |
| `heightFillParent` | `boolean` | Match parent height |

#### CreateDynamicLabel
Creates and adds a dynamic label to a layout.

| Parameter | Type | Description |
|-----------|------|-------------|
| `text` | `text` | Label text |
| `layoutComponent` | `component` | Parent layout |
| `labelId` | `number` | Unique ID |
| `isBold` | `boolean` | Bold style |
| `isItalic` | `boolean` | Italic style |
| `textColor` | `number` | Text color |
| `width` | `number` | Width in pixels |
| `height` | `number` | Height in pixels |
| `widthFillParent` | `boolean` | Match parent width |
| `heightFillParent` | `boolean` | Match parent height |
| `isVisible` | `boolean` | Visibility |
| `textSize` | `number` | Text size (sp) |
| `backgroundColor` | `number` | Background color |
| `acceptsHtml` | `boolean` | Enable HTML |
| `horizontalAlignment` | `helper` | Layout horizontal alignment |
| `verticalAlignment` | `helper` | Layout vertical alignment |
| `horizontalAlignmentText` | `helper` | Text horizontal alignment |
| `verticalAlignmentText` | `helper` | Text vertical alignment |

#### CreateDynamicTextBox
Creates and adds a dynamic TextBox to a layout.

| Parameter | Type | Description |
|-----------|------|-------------|
| `hint` | `text` | Hint text |
| `layoutComponent` | `component` | Parent layout |
| `textBoxId` | `number` | Unique ID |
| `width` | `number` | Width in pixels |
| `height` | `number` | Height in pixels |
| `widthFillParent` | `boolean` | Match parent width |
| `heightFillParent` | `boolean` | Match parent height |
| `isMultiline` | `boolean` | Multi-line support |
| `hintColor` | `number` | Hint text color |
| `textColor` | `number` | Text color |
| `isBold` | `boolean` | Bold style |
| `isItalic` | `boolean` | Italic style |
| `isVisible` | `boolean` | Visibility |
| `textSize` | `number` | Text size (sp) |
| `backgroundColor` | `number` | Background color |
| `horizontalAlignment` | `helper` | Layout alignment |
| `textAlignment` | `helper` | Text alignment |

#### GetDynamicTextBoxText
Gets the current text or hint of a dynamic TextBox.

| Parameter | Type | Description |
|-----------|------|-------------|
| `textBoxId` | `number` | TextBox ID |
| `isHint` | `boolean` | True for hint, false for text |

```
Returns: text
```

### Layout Components

#### CreateDynamicSpace
Creates and adds a dynamic Space to a layout.

| Parameter | Type | Description |
|-----------|------|-------------|
| `layoutComponent` | `component` | Parent layout |
| `spaceId` | `number` | Unique ID |
| `width` | `number` | Width in pixels |
| `height` | `number` | Height in pixels |
| `widthFillParent` | `boolean` | Match parent width |
| `heightFillParent` | `boolean` | Match parent height |

#### CreateDynamicVerticalArrangement
Creates and adds a dynamic Vertical Arrangement to a layout.

| Parameter | Type | Description |
|-----------|------|-------------|
| `layoutComponent` | `component` | Parent layout |
| `verticalArrangementId` | `number` | Unique ID |
| `isVisible` | `boolean` | Visibility |
| `width` | `number` | Width in pixels |
| `height` | `number` | Height in pixels |
| `widthFillParent` | `boolean` | Match parent width |
| `heightFillParent` | `boolean` | Match parent height |
| `backgroundColor` | `number` | Background color |
| `horizontalAlignment` | `helper` | Horizontal alignment |
| `verticalAlignment` | `helper` | Vertical alignment |

#### CreateDynamicHorizontalArrangement
Creates and adds a dynamic Horizontal Arrangement to a layout.

| Parameter | Type | Description |
|-----------|------|-------------|
| `layoutComponent` | `component` | Parent layout |
| `horizontalArrangementId` | `number` | Unique ID |
| `isVisible` | `boolean` | Visibility |
| `width` | `number` | Width in pixels |
| `height` | `number` | Height in pixels |
| `widthFillParent` | `boolean` | Match parent width |
| `heightFillParent` | `boolean` | Match parent height |
| `backgroundColor` | `number` | Background color |
| `horizontalAlignment` | `helper` | Horizontal alignment |
| `verticalAlignment` | `helper` | Vertical alignment |

#### RemoveComponentById
Removes a dynamic component by its ID.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Component ID to remove |

### Media Components

#### CreateDynamicCardView
Creates and adds a dynamic CardView to a layout.

| Parameter | Type | Description |
|-----------|------|-------------|
| `cardTitle` | `text` | Card title |
| `cardText` | `text` | Card description |
| `layoutComponent` | `component` | Parent layout |
| `cardViewId` | `number` | Unique ID |
| `titleTextColor` | `number` | Title color |
| `textTextColor` | `number` | Text color |
| `cardBackgroundColor` | `number` | Background color |
| `isVisible` | `boolean` | Visibility |
| `titleTextSize` | `number` | Title text size |
| `textTextSize` | `number` | Body text size |
| `setRadius` | `number` | Corner radius |
| `profileImagePath` | `text` | Image path/URL |
| `profileImageVisible` | `boolean` | Show image |
| `cardWidth` | `number` | Width in pixels |
| `cardHeight` | `number` | Height in pixels |
| `widthFillParent` | `boolean` | Match parent width |
| `heightFillParent` | `boolean` | Match parent height |
| `marginList` | `list` | Margins [left, top, right, bottom] |
| `textAlignment` | `helper` | Text alignment |
| `verticalAlignment` | `helper` | Card vertical alignment |
| `horizontalAlignment` | `helper` | Card horizontal alignment |
| `textImageProfileAlignment` | `helper` | Image/text arrangement |

#### CreateDynamicImage
Creates and adds a dynamic ImageView to a layout.

| Parameter | Type | Description |
|-----------|------|-------------|
| `imagePath` | `text` | Image path or URL |
| `layoutComponent` | `component` | Parent layout |
| `imageId` | `number` | Unique ID |
| `width` | `number` | Width in pixels |
| `height` | `number` | Height in pixels |
| `widthFillParent` | `boolean` | Match parent width |
| `heightFillParent` | `boolean` | Match parent height |
| `isVisible` | `boolean` | Visibility |

#### CreateImageSlider
Creates a dynamic image slider with ViewPager.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Unique ID |
| `imageUrls` | `list` | List of image paths/URLs |
| `layoutComponent` | `component` | Parent layout |
| `viewPagerWidth` | `number` | Slider width (-1 for match parent) |
| `viewPagerHeight` | `number` | Slider height (-1 for match parent) |
| `imageWidth` | `number` | Image width (-1 for match parent) |
| `imageHeight` | `number` | Image height (-1 for match parent) |

### Properties & Utilities

#### SetComponentById
Configures common component properties.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Component ID |
| `text` | `text` | Text content |
| `textColor` | `number` | Text color |
| `backgroundColor` | `number` | Background color |
| `visibility` | `number` | Visibility (0=visible, 8=gone) |
| `textSize` | `number` | Text size |
| `textAlignment` | `number` | Text alignment |
| `horizontalAlignment` | `number` | Horizontal alignment |
| `verticalAlignment` | `number` | Vertical alignment |
| `marginList` | `list` | Margins [left, top, right, bottom] |
| `width` | `number` | Width |
| `height` | `number` | Height |
| `widthFillParent` | `boolean` | Match parent width |
| `heightFillParent` | `boolean` | Match parent height |

#### SetTextColor
Sets the text color of a dynamic component.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Component ID |
| `textColor` | `text` | Color as text (e.g., "#FF0000") |

#### SetBackgroundColor
Sets the background color of a dynamic component.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Component ID |
| `backgroundColor` | `number` | Color value |

#### SetSize
Sets the width and height of a dynamic component.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Component ID |
| `width` | `number` | New width |
| `height` | `number` | New height |

#### SetText
Sets the text of a dynamic component.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Component ID |
| `text` | `text` | New text |

#### SetVerticalAlignmentRelativeTo
Sets vertical alignment relative to another component.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Component to align |
| `referenceComponentId` | `number` | Reference component |
| `alignment` | `helper` | Alignment value |

#### SetHorizontalAlignmentRelativeTo
Sets horizontal alignment relative to another component.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Component to align |
| `referenceComponentId` | `number` | Reference component |
| `alignment` | `helper` | Alignment value |

#### SetHorizontalTextAlignment
Sets horizontal text alignment for text components.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Component ID |
| `alignment` | `helper` | Text alignment |

#### GetComponentInfo
Gets a list of all component information.

```
Returns: list - Each item contains [componentName, componentId, layoutName]
```

#### GetDynamicComponentById
Gets a dynamic component by its ID.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Component ID |

```
Returns: component
```

#### GetDynamicComponentHeight
Gets the height of a dynamic component.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Component ID |

```
Returns: number
```

#### GetDynamicComponentWidth
Gets the width of a dynamic component.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Component ID |

```
Returns: number
```

#### GetDynamicComponentBackgroundColor
Gets the background color of a dynamic component.

| Parameter | Type | Description |
|-----------|------|-------------|
| `componentId` | `number` | Component ID |

```
Returns: number
```

---

## 🎨 Designer Properties

### FontTypeface
Sets the default font typeface for component text.

| Property | Value |
|----------|-------|
| **Type** | `typeface` |
| **Default** | `0` (Default) |
| **Setter** | `FontTypeface(number)` |
| **Getter** | `FontTypeface` returns `number` |

---

## 🔧 Helper Enums

### AlignmentHorizontal

| Value | Description |
|-------|-------------|
| `LEFT` | Align to left |
| `CENTER` | Center horizontally |
| `RIGHT` | Align to right |

### AlignmentVertical

| Value | Description |
|-------|-------------|
| `TOP` | Align to top |
| `CENTER` | Center vertically |
| `BOTTOM` | Align to bottom |

### AlignmentText

| Value | Description |
|-------|-------------|
| `LEFT` | Text aligned left |
| `CENTER` | Text centered |
| `RIGHT` | Text aligned right |

---

## 📦 Installation

1. Download the `.aix` file
2. In MIT App Inventor, go to the **Palette** section
3. Click **Import Extension**
4. Select the downloaded file
5. The extension will appear in the **Extension** drawer

---

## 🚀 Quick Example

```blocks
Initialize App
  call DynamicComponents1.GenerateUniqueId
  set global componentId to get result

  call DynamicComponents1.CreateDynamicButton
    text: "Click Me!"
    layoutComponent: HorizontalArrangement1
    buttonId: get global componentId
    textColor: COLOR_BLACK
    isBold: true
    isItalic: false
    isVisible: true
    textSize: 14
    backgroundColor: COLOR_LTGRAY
    width: 100
    height: 50
    widthFillParent: false
    heightFillParent: false

when DynamicComponents1.Click
  (componentId)
    call DynamicComponents1.SetText
      componentId: componentId
      text: "Clicked!"
```

---

## 📝 License

This extension is provided as-is. Feel free to use and modify for your projects.

---

<div align="center">
  <p>Built with ❤️ using <a href="https://community.appinventor.mit.edu/t/fast-an-efficient-way-to-build-extensions/129103?u=jewel">FAST CLI</a></p>
  <p><small>Version 1.2 | Last Updated: 2025-05-24</small></p>
</div>