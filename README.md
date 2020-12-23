# Custom Component with State

## ImageWithLabel

Custom component that exposes its state `isSelected` (LiveData<Boolean>).

### ImageWithLabelBindingAdapter

- `setIsSelected()`: responsible to update the ImageWithLabel `isSelected` value. Annotated with `@BindingAdapter("app:is_selected")` to create the attribute to be used in XML layout files.
- `getSelected()`: responsible to update the observer when there is a change in the `isSelected` value. Annotated with `@InverseBindingAdapter(attribute = "app:is_selected", event = "app:is_selectedAttrChanged")`:
   - `attribute = "app:is_selected"`: the attribute to be used in XML layout files to obtain a two-way data binding.
   - `event = "app:is_selectedAttrChanged"`: link to the function that will define when getSelected()` must be called in order to update the observer.
- `setItemSelectedListeners()`: responsible to define when the `attrChange.onChange()` will be called, resulting in the execution of `getSelected()`. Annotated with `@BindingAdapter("app:is_selectedAttrChanged")`.

## SpinnerAdapter

Just simple binding adapter for the AppCompatSpinner:

- `setItems()`: responsible to create an ArrayAdapter<String> and set it into the AppCompatSpinner, removing the need of having this code repeated in a Activities or Fragments.
- `setItemSelected()`, `getItemSelected()` and `setItemSelectedListeners()`: The same idea explained in the `ImageWithLabelBindingAdapter` section. `setItemSelected()` used to update the selected item in the Spinner according the to observer value, `getItemSelected()` to update the observer with the user selected item and `setItemSelectedListeners()` to define when the observer must be updated with the selected item.