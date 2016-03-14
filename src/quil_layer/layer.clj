(ns quil-layer.layer)

(defprotocol Layer
  (update-layer-state [this state])
  (draw-layer-state [this state]))
