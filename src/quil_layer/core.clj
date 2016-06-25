(ns quil-layer.core
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:use [quil-layer layer layers]
        [quil-layer.layers background-layer fadeout-layer layer-example text-layer]))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :rgb)
  (q/background 128))

(defn update-state [state]
  (update-layers)
  state)

(defn draw-state [state]
  (draw-layers))

(q/defsketch quil-layer
  :title "quil-layer example"
  :size :fullscreen
  :setup setup
  :update update-state
  :draw draw-state
  :middleware [m/fun-mode])

(comment
  ;; to add background-layer, evaluate following do clause.
  (do
    (def bglayer (->BackgroundLayer (atom {})))
    (let [layer bglayer]
      (setup-layer layer)
      ;; (reset! oneoff-fn #(do (q/color-mode :rgb) (q/background 255 155 155)))
      ;; (reset! oneoff-fn #(do (q/color-mode :rgb) (q/background 100 100 100)))
      ;; (reset! bg-alpha 10)
      (add-layer layer)))
  ;; to remove background-layer, evaluate following clause.
  (remove-layer bglayer)
  )

(comment
  ;; to add example-layer, evaluate following do clause.
  (do
    (def layerex (->LayerExample (atom {})))
    (let [layer layerex]
      (setup-layer layer)
      (add-layer layer)))
  ;; to remove example-layer, evaluate following clause.
  (remove-layer layerex)
  )

(comment
  ;; to add text-layer, evaluate following do clause.
  (do
    (def textlayer (->TextLayer (atom {})))
    (let [layer textlayer]
      (setup-layer layer)
      (add-layer layer)))
  ;; to remove text-layer, evaluate following clause.
  (remove-layer textlayer)
  )

(comment
  ;; to add fadeout-layer, evaluate following do clause.
  (do
    (def fadeoutlayer (->FadeoutLayer 3000 (atom {})))
    (let [layer fadeoutlayer]
      (setup-layer layer)
      (add-layer layer)))
  ;; (println fadeoutlayer)
  ;; to remove fadeout-layer, evaluate following clause.
  (remove-layer fadeoutlayer)
  )
