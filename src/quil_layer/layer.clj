(ns quil-layer.layer)

(defprotocol Layer
  (setup-layer-state [this])
  (update-layer-state [this state])
  (draw-layer-state [this state]))

(defn setup-layer [layer]
  (let [state (:state layer)
        new-state (setup-layer-state layer)]
    (reset! state new-state)))

(defn update-layer [layer]
  (let [state (:state layer)
        new-state (update-layer-state layer @state)]
    (reset! state new-state)))

(defn draw-layer [layer]
  (draw-layer-state layer @(:state layer)))
