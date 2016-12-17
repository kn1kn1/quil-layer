# quil-layer

A layer system for Quil.

## Usage (example project)

#### Emacs

* run cider
* open `src/quil-layer/core.clj`
* press `C-c C-k` to evaluate the file.

* press `C-c C-e` to evaluate the some regions.

* to add background
```clojure
;; to add background-layer, evaluate following do clause.
(do
  (def bglayer (->BackgroundLayer (atom {})))
  (let [layer bglayer]
    (setup-layer layer)
    ;; (reset! oneoff-fn #(do (q/color-mode :rgb) (q/background 255 155 155)))
    ;; (reset! oneoff-fn #(do (q/color-mode :rgb) (q/background 100 100 100)))
    ;; (reset! bg-alpha 10)
    (add-layer layer)))
```
* to add example layer
```clojure
;; to add example-layer, evaluate following do clause.
(do
  (def layerex (->LayerExample (atom {})))
  (let [layer layerex]
    (setup-layer layer)
    (add-layer layer)))
```

* to add text layer
```clojure
;; to add text-layer, evaluate following do clause.
(do
  (def textlayer (->TextLayer (atom {})))
  (let [layer textlayer]
    (setup-layer layer)
    (add-layer layer)))
```

## License

Copyright © 2016 kn1kn1

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
