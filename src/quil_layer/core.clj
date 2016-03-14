(ns quil-layer.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defprotocol Layer
  (update-layer-state [this state])
  (draw-layer-state [this state]))

(defrecord MyLayer [state]
  Layer
  (update-layer-state [this state]
    ; Update sketch state by changing circle color and position.
    {:color (mod (+ (:color state) 0.7) 255)
     :angle (+ (:angle state) 0.1)})

  (draw-layer-state [this state]
    ; Clear the sketch by filling it with light-grey color.
    ;(q/background 240)

    (q/no-stroke)
    (q/fill 0 2)
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
        (q/ellipse x y 100 100)))))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  ;(q/color-mode :rgb)
  (q/background 0)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:layer (->MyLayer {:color 0 :angle 0})})

(defn update-layer [layer]
  (let [new-state (update-layer-state layer (:state layer))
      new-layer (assoc layer :state new-state)]
    new-layer))

(defn update-state [state]
  (let [layer (:layer state)
        new-layer (update-layer layer)]
    {:layer new-layer}))

(defn draw-layer [layer]
    (draw-layer-state layer (:state layer)))

(defn draw-state [state]
  (let [layer (:layer state)]
    (draw-layer layer)))

(q/defsketch quil-layer
  :title "You spin my circle right round"
  :size [500 500]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  ; :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])
