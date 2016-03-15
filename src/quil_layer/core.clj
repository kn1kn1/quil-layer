(ns quil-layer.core
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:use [quil-layer layer layers]
        [quil-layer.layers fadeout-layer layer-example layer-example2]))

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
    (def layerex (->LayerExample (atom {})))
    (let [layer layerex]
      (setup-layer layer)
      (add-layer layer)))
  (remove-layer layerex)
  )

(comment
  (do
    (def layerex2 (->LayerExample2 (atom {})))
    (let [layer layerex2]
      (setup-layer layer)
      (add-layer layer)))
  (remove-layer layerex2)
  )

  (comment
    (do
      (def fadeoutlayer (->FadeoutLayer 5000 (atom {})))
      (let [layer fadeoutlayer]
        (setup-layer layer)
        (add-layer layer)))
    (println fadeoutlayer)
    (remove-layer fadeoutlayer)
    )

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
