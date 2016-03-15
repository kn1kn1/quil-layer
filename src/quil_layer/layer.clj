(ns quil-layer.layer)

(defprotocol Layer
  (setup-layer-state [this])
  (update-layer-state [this state])
  (draw-layer-state [this state]))
