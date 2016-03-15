(ns quil-layer.core
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:use [quil-layer layer layers my-layer my-layer2]))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  ;(q/color-mode :rgb)
  (q/color-mode :hsb)
  ; (q/background 128 255 255)
  (q/background 128)
)

(defn update-state [state]
  (update-layers)
  state)

(defn draw-state [state]
  (draw-layers))

(comment
  (do
    (def mylayer-rec (->MyLayer (atom {})))
    (let [layer mylayer-rec]
      (setup-layer layer)
      (add-layer layer)))
  )
(comment
  (remove-layer mylayer-rec)
  )


(comment
  (let [layer (->MyLayer (atom {}))]
    (setup-layer layer)
    (add-layer layer)))

(comment
  (let [layer (->MyLayer2 (atom {}))]
    (setup-layer layer)
    (add-layer-to-bottom layer)))

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
