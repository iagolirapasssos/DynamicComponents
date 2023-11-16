# DynamicComponents
Introducing the DynamicsComponents Extension, an innovative tool designed to streamline the creation and management of dynamic components in Android applications using Kodular Creator, Niotron and MIT App Inventor. This extension offers a range of functionalities for adding dynamic, interactive elements to your apps with ease.

# GenerateUniqueId

Synchronous method to generate a unique integer ID.

This method does not take any parameters. It calls the `UniqueIdGenerator` helper class to generate a unique ID and returns it. The returned ID can be used to identify components.

# CreateDynamicButton 

Asynchronous method to create a dynamic button and add it to a layout.

Parameters:

- `text` - String, text to display on the button 
- `layoutComponent` - AndroidViewComponent, the parent layout to add the button to
- `buttonId` - int, unique ID for the button
- `textColor` - int, text color of the button
- `isBold` - boolean, whether to make button text bold
- `isItalic` - boolean, whether to make button text italic
- `isVisible` - boolean, whether the button is visible initially 
- `textSize` - int, text size of the button text
- `backgroundColor` - int, background color of the button
- `width` - int, width of the button
- `height` - int, height of the button
- `widthFillParent` - boolean, whether width should match parent
- `heightFillParent` - boolean, whether height should match parent

This method creates a `MyButtonComponent`, configures its properties based on the parameters, sets click listeners, and adds it to the `layoutComponent`. It dispatches `Click` and `DoubleClick` events when the button is clicked.

# CreateDynamicLabel

Asynchronous method to create a dynamic label and add it to a layout.

Parameters:

- `text` - String, text to display in the label
- `layoutComponent` - AndroidViewComponent, the parent layout 
- `labelId` - int, unique ID for the label
- `isBold` - boolean, make label text bold
- `isItalic` - boolean, make label text italic
- `textColor` - int, text color of label
- `width` - int, width of label
- `height` - int, height of label
- `widthFillParent` - boolean, match parent width
- `heightFillParent` - boolean, match parent height  
- `isVisible` - boolean, initial visibility of label
- `textSize` - int, text size of label
- `backgroundColor` - int, background color of label
- `acceptsHtml` - boolean, whether label text contains HTML
- `horizontalAlignment` - int, horizontal alignment of label
- `verticalAlignment` - int, vertical alignment of label
- `horizontalTextAlignment` - int, horizontal text alignment
- `verticalTextAlignment` - int, vertical text alignment

This method creates a `MyLabelComponent`, configures it based on the parameters, sets click listeners, and adds it to the `layoutComponent`. It dispatches `Click` and `DoubleClick` events when clicked.

# CreateDynamicTextBox

Asynchronous method to create a dynamic text box and add it to a layout.

Parameters:

- `hint` - String, hint text to display
- `layoutComponent` - AndroidViewComponent, parent layout
- `textBoxId` - int, unique ID for the text box  
- `width` - int, width of text box
- `height` - int, height of text box
- `widthFillParent` - boolean, match parent width  
- `heightFillParent` - boolean, match parent height
- `isMultiline` - boolean, multiline input
- `hintColor` - int, hint text color
- `textColor` - int, input text color 
- `isBold` - boolean, bold input text
- `isItalic` - boolean, italic input text
- `isVisible` - boolean, initial visibility
- `textSize` - int, input text size
- `backgroundColor` - int, background color
- `horizontalAlignment` - int, horizontal alignment 
- `textAlignment` - int, alignment of input text

This method creates a `MyTextBoxComponent`, configures it based on the parameters, sets text change listener, and adds it to the `layoutComponent`. 

It dispatches `TextChangedEvent` when text changes and `Click` when clicked.

# CreateDynamicCardView 

Asynchronous method to create a CardView with image, title and text.

Parameters:

- `cardTitle` - String, card title text 
- `cardText` - String, card description text
- `layoutComponent` - AndroidViewComponent, parent layout
- `cardViewId` - int, unique ID for the card
- `titleTextColor` - int, card title text color
- `textTextColor` - int, card description text color
- `cardBackgroundColor` - int, card background color
- `isVisible` - boolean, initial visibility of card
- `titleTextSize` - int, card title text size
- `textTextSize` - int, card description text size  
- `setRadius` - int, radius for rounded corners
- `profileImagePath` - String, file path of image  
- `profileImageVisible` - boolean, whether to show profile image
- `cardWidth` - int, width of card
- `cardHeight` - int, height of card
- `widthFillParent` - boolean, match parent width
- `heightFillParent` - boolean, match parent height
- `marginList` - YailList, margins around the card as [left, top, right, bottom] 
- `textAlignment` - int, alignment of text in card
- `verticalAlignment` - int, vertical alignment of card
- `horizontalAlignment` - int, horizontal alignment of card
- `textImageProfileAlignment` - int, alignment of image and text

This method creates a `MyCardViewComponent` configured based on the parameters. It adds `TextView`s, `ImageView` and sets alignments. The card is added to the `layoutComponent`.

# CreateDynamicSpace

Asynchronous method to create a dynamic space (empty view) and add it to a layout.

Parameters:

- `layoutComponent` - AndroidViewComponent, parent layout 
- `spaceId` - int, unique ID for the space
- `width` - int, width of the space
- `height` - int, height of the space
- `widthFillParent` - boolean, match parent width
- `heightFillParent` - boolean, match parent height

This creates a `MySpaceComponent` with given size, sets its ID, and adds it to the `layoutComponent`.

# CreateDynamicVerticalArrangement

Asynchronous method to create a vertical linear layout.

Parameters:

- `layoutComponent` - AndroidViewComponent, parent layout
- `verticalArrangementId` - int, unique ID  
- `isVisible` - boolean, initial visibility
- `width` - int, width of layout
- `height` - int, height of layout
- `widthFillParent` - boolean, match parent width
- `heightFillParent` - boolean, match parent height 
- `backgroundColor` - int, background color
- `horizontalAlignment` - int, horizontal alignment
- `verticalAlignment` - int, vertical alignment of children

This creates a `MyVerticalArrangementComponent` with a vertical `LinearLayout`. It sets the ID, visibility, size, background color and alignments based on parameters. It is added to the `layoutComponent`.

Dispatches `Click` and `DoubleClick` events when clicked.

# CreateDynamicHorizontalArrangement 

Asynchronous method to create a horizontal linear layout.

Parameters: 

- `layoutComponent` - AndroidViewComponent, parent layout
- `horizontalArrangementId` - int, unique ID
- `isVisible` - boolean, initial visibility 
- `width` - int, width of layout
- `height` - int, height of layout
- `widthFillParent` - boolean, match parent width
- `heightFillParent` - boolean, match parent height
- `backgroundColor` - int, background color
- `horizontalAlignment` - int, horizontal alignment
- `verticalAlignment` - int, vertical alignment of children

This creates a `MyHorizontalArrangementComponent` with a horizontal `LinearLayout`. It sets the ID, visibility, size, background color and alignments based on parameters. It is added to the `layoutComponent`.

Dispatches `Click` and `DoubleClick` events when clicked.

# CreateDynamicImage

Asynchronous method to create a dynamic ImageView and load an image. 

Parameters:

- `imagePath` - String, path or URL of image to load
- `layoutComponent` - AndroidViewComponent, parent layout 
- `imageId` - int, unique ID for the image
- `width` - int, image width
- `height` - int, image height
- `widthFillParent` - boolean, match parent width
- `heightFillParent` - boolean, match parent height
- `isVisible` - boolean, initial visibility

This method creates a `MyImageComponent` and tries to load the image from the given `imagePath` which can be a URL or local file path. The image is loaded asynchronously using `DownloadImageTask`. 

It sets the size and visibility based on parameters before adding to the `layoutComponent`.

# SetVerticalAlignmentRelativeTo

Synchronous method to set a component's vertical alignment relative to another component.

Parameters:

- `componentId` - int, ID of component to align
- `referenceComponentId` - int, ID of reference component 
- `alignment` - int, vertical alignment (top, center, bottom)

This method looks up the two components by their IDs, gets their layout params and sets the vertical gravity to achieve the alignment.

# SetHorizontalAlignmentRelativeTo

Synchronous method to set a component's horizontal alignment relative to another component.

Parameters:

- `componentId` - int, ID of component to align
- `referenceComponentId` - int, ID of reference component
- `alignment` - int, horizontal alignment (left, center, right)

This method looks up the two components by their IDs, gets their layout params and sets the horizontal gravity to achieve the alignment.

# SetHorizontalTextAlignment

Synchronous method to set the horizontal alignment of text within a component like label or text box.

Parameters:

- `componentId` - int, ID of the component 
- `alignment` - int, text alignment (left, center, right)

This method looks up the component by ID and if it is a TextView, sets the gravity to the alignment.

# CreateImageSlider

Asynchronous method to create an image slider/gallery using a ViewPager.

Parameters:

- `componentId` - int, unique ID for the image slider
- `imageUrls` - List, URLs of images to display
- `layoutComponent` - AndroidViewComponent, parent layout
- `viewPagerWidth` - int, width of the ViewPager
- `viewPagerHeight` - int, height of the ViewPager 
- `imageWidth` - int, width to render each image
- `imageHeight` - int, height to render each image

This method creates a `MyViewPagerComponent` and `ImageSliderAdapter`. It loads images asynchronously from the URLs and sets up the ViewPager. The ViewPager is added to the `layoutComponent`.

# SetComponentById

Synchronous method to configure common properties of a component.

Parameters:

- `componentId` - int, ID of the component
- `text` - String, text content if applicable
- `textColor` - int, text color if applicable  
- `backgroundColor` - int, background color
- `visibility` - int, visibility (VISIBLE, GONE, INVISIBLE)
- `textSize` - int, text size if applicable
- `textAlignment` - int, text alignment if applicable
- `horizontalAlignment` - int, horizontal alignment
- `verticalAlignment` - int, vertical alignment 
- `marginList` - YailList, margins as [left, top, right, bottom]
- `width` - int, width of component
- `height` - int, height of component
- `widthFillParent` - boolean, match parent width
- `heightFillParent` - boolean, match parent height

This looks up the component by `componentId` and sets its common properties like text, color, size, alignments, margins, etc. based on the parameters.

# SetTextColor 

Synchronous method to set the text color of a component.

Parameters:

- `componentId` - int, ID of the component
- `textColor` - int, color value for the text

Looks up the component by ID and sets the text color if it is a TextView.

# SetBackgroundColor

Synchronous method to set the background color of a component.

Parameters:

- `componentId` - int, ID of the component
- `backgroundColor` - int, color value for the background

Looks up the component by ID and sets its background color.

# SetSize

Synchronous method to set the size of a component.

Parameters: 

- `componentId` - int, ID of the component
- `width` - int, width in pixels
- `height` - int, height in pixels

Looks up the component by ID and sets its width and height layout parameters.

# SetText

Synchronous method to set text content of a component like label or text box.

Parameters:

- `componentId` - int, ID of the component 
- `text` - String, new text to set

Looks up the component by ID and sets its text if it is a TextView.

# GetComponentInfo

Synchronous method to get information about the created dynamic components.

No parameters.

Returns a YailList of sublists containing [componentName, componentId, layoutName] for each dynamic component.

# GetDynamicComponentById

Synchronous method to lookup a dynamic component by its ID.

Parameter:

- `componentId` - int, ID of the component

Returns the `AndroidViewComponent` associated with the ID, or null if not found.

# GetDynamicTextBoxText

Synchronous method to get the current text or hint from a dynamic text box.

Parameters:

- `textBoxId` - int, ID of the text box component
- `isHint` - boolean, whether to return hint or actual text

Returns a String containing the hint or text content.

# GetDynamicComponentHeight

Synchronous method to get the height of a dynamic component.

Parameter: 

- `componentId` - int, ID of the component

Returns the height in pixels, or 0 if component not found.

# GetDynamicComponentWidth

Synchronous method to get the width of a dynamic component.

Parameter:

- `componentId` - int, ID of the component 

Returns the width in pixels, or 0 if component not found.

# GetDynamicComponentBackgroundColor

Synchronous method to get the background color of a dynamic component.

Parameter:

- `componentId` - int, ID of the component

Returns the background color int value, or 0 if unable to determine.

# Click

Asynchronous event dispatched when a component is clicked.

Parameter:

- `componentId` - int, ID of the clicked component

# ReportError

Asynchronous event to report an error.

Parameter: 

- `errorMessage` - String, description of the error

# DoubleClick 

Asynchronous event dispatched when a component is double clicked.

Parameter:

- `componentId` - int, ID of the double clicked component.

# ComponentCreated

Asynchronous event dispatched after creating a component. 

Parameters:

- `componentName` - String, name of the created component 
- `componentId` - int, ID of the created component

# TextChangedEvent

Asynchronous event dispatched when text changes in a text box.

Parameters:

- `textBoxId` - int, ID of the text box
- `newText` - String, updated text content