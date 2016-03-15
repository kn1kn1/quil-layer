(ns quil-layer.my-layer2
  (:require [quil.core :as q])
  (:use [quil-layer.layer]))

(defn- setup []
  {:color 0
   :angle 0})

(defn- update-state [state]
  ; Update sketch state by changing circle color and position.
  {:color (mod (+ (:color state) 0.7) 255)
   :angle (+ (:angle state) 0.1)})

(defn- draw-state [state]
  ; Clear the sketch by filling it with light-grey color.
  ;(q/background 240)

  (q/no-stroke)
  (q/color-mode :hsb)
  (q/fill (.backgroundColor (q/current-graphics)) 1)
  (q/rect 0 0 (q/width) (q/height))

  ; Set circle color.
  (q/fill (:color state) 255 255)
  (q/with-translation [(/ (q/width) 2)
                       (/ (q/height) 2)]
    ; Draw the circle.
    (q/ellipse 0 0 (* 2 (:color state)) (* 2 (:color state))))
  )

(defrecord MyLayer2 [state]
  Layer
  (setup-layer-state [this]
                     (setup))
  (update-layer-state [this state]
                      (update-state state))
  (draw-layer-state [this state]
                    (draw-state state)))
