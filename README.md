<div align="center">

# Skope
Just another Skript addon, an addon I'm working on currently.

</div>

## Custom GUI (MiWoSe)
```skript
[skope] open [a] [new] custom (GUI|gui) (with|of) id %string% with %integer% rows (with name|named) %string% to [player] %player%

#Example:
open a new custom gui with id "potion_menu" with 4 rows named "Potion Menu" to player
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
