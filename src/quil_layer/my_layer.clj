(ns quil-layer.my-layer
  (:require [quil.core :as q])
  (:use [quil-layer.layer]))

(defn- my-update [state]
  ; Update sketch state by changing circle color and position.
  {:color (mod (+ (:color state) 0.7) 255)
   :angle (+ (:angle state) 0.1)})

(defn- my-draw [state]
  ; Clear the sketch by filling it with light-grey color.
  ;(q/background 240)

  (q/no-stroke)
  (q/color-mode :hsb)
  (q/fill (.backgroundColor (q/current-graphics)) 1)
  (q/rect 0 0 (q/width) (q/height))

  ; Set circle color.
  (q/fill (:color state) 255 255)
  ; Calculate x and y coordinates of the circle.
  (let [angle (:angle state)
        x (* 150 (q/cos angle))
        y (* 150 (q/sin angle))]
    ; Move origin point to the center of the sketch.
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ; Draw the circle.
      (q/ellipse x y 100 100))))

(defrecord MyLayer [state]
  Layer
  (update-layer-state [this state]
    (my-update state))
  (draw-layer-state [this state]
    (my-draw state)))
