<div align="center">

# Skope
Just another Skript addon, created and maintained for [MiWoSe](https://miwose.tk).

</div>

## Custom GUI (MiWoSe)
```skript
[skope] open [a] [new] (custom|miwose) (GUI|gui) (with|of) id %string% with %integer% row[s] (with name|named) %string% to [player] %player%

#Example:
open a new custom gui with id "potion_menu" with 4 rows named "Potion Menu" to player
```

## Open GUIs

Copies TuSKe's syntax - so we needn't change everything after getting rid of it (as it is no longer updated).
Add `skope` prefix to avoid issues if using both addons.

```skript
[skope] open [(a|an)] anvil named %string% to [player] %player%
[skope] (show|open) virtual anvil [inventory] named %string% (to|for) [player] %player%

#Example:
skope open anvil named "Custom anvil" to player
```

The same works for dispensers, droppers and hoppers!
(Use vanilla syntax for GUIs without names - `open anvil for player`.)

### Chest inventory (TuSKe's syntax)

```skript
[skope] (show|open) virtual chest [inventory] with %integer% row[s] named %string% (to|for) [player] %player%

#Example:
skope open virtual chest inventory with 3 rows named "Custom chest" to player
```

## Item in item frame

```skript
[the] [skope] shown item on [item frame] %entity%

#Example:
set {_item} to the shown item on item frame {_itemFrame}
```

```skript
[skope] set [the] shown item on [item frame] %entity% to %itemstack%

#Example:
set the shown item on item frame {_itemFrame} to {_newItem}
```

## Books

### 1. Create a new book:
```skript
set {_book} to [a] [new] [skope] custom book [(with name|named|with title|titled) %-string%] [(with author|authored by) %-string%] [(with|of) generation %-integer%]

#Example:
set {_book} to a new custom book with title "My book" of generation 2
```

### 2. Create a page
```skript
set {_page} to [a] [new] [skope] custom [book] page [with text %-string%]

#Example:
set {_book} to a new custom page
```

### 3. Edit the page
```skript
[skope] add [plain] text %string% to [(a|the)] custom [book] page %page%

#Example:
add text "&6&lMy awesome book" to custom page {_page}
```

```skript
set {_tc} to [a] [new] [skope] text component [with text] %string% (...)
    ... to run [(a|the)] command %string% on click
    ... to suggest [(a|the)] command %string% on click
    ... to open (url|link) %string% on click
    ... to change page to %integer% on click
    ... to show text %string% on hover
    ... to show item %itemstack% on hover
remove click/hover action from [(a|the)] text component %textcomponent%
set click/hover action of text component %textcomponent% to (...)
[skope] add text component %textcomponent% to [(a|the)] custom [book] page %page%

#Example:
set {_tc} to a new text component "Ahoj" to run command "/lobby" on click
remove click action from text component {_tc}
set hover action of text component {_tc} to show text "Sneakyyy!"
add text component {_tc} to custom page {_page}
```

```skript
[skope] add [a] new line to [(a|the)] custom [book] page %page%
#OR
[skope] add %integer% [of] new lines to [(a|the)] custom [book] page %page%

#Example:
add a new line to custom page {_page}
#OR
add 3 new lines to custom page {_page}
```

### 4. Add the page to the book
```skript
[skope] add [(a|the)] custom [book] page %page% to [(a|the)] custom book %book%

#Example:
add custom page {_page} to custom book {_book}
```

### 5. Use the book!
```skript
[skope] open [(a|the)] custom book %book% to [player] %player%

#Example:
open custom book {_book} to player
```


```skript
[skope] give [(a|the)] custom book %object% to [player] %player%
#OR
[skope] give [player] %player% [(a|the)] custom book %object%

#Example:
give the custom book {_book} to player
#OR
give player the custom book {_book}
```
