(ns quil-layer.core
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:use [quil-layer layer my-layer my-layer2]))

(def layers (atom []))

(defn- add-layer [layer]
  (let [state (:state layer)
        new-state (setup-layer layer)]
    (reset! state new-state)
    (swap! layers conj layer)))

(defn- update-layer [layer]
  (let [state (:state layer)
        new-state (update-layer-state layer @state)]
      (reset! state new-state)))

(defn- draw-layer [layer]
    (draw-layer-state layer @(:state layer)))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  ;(q/color-mode :rgb)
  (q/color-mode :hsb)
  ; (q/background 128 255 255)
  (q/background 128)

  (let [layer (->MyLayer (atom {}))]
    (add-layer layer))
  (let [layer (->MyLayer2 (atom {}))]
    (add-layer layer)))

(defn update-state [state]
  ;(println layers)
  (dorun (for [layer @layers] (update-layer layer)))
  state)

(defn draw-state [state]
  (dorun(for [layer @layers] (draw-layer layer))))

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
