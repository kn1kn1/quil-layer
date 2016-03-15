(ns quil-layer.layers
  (:use [quil-layer layer]))

(defonce layers (atom []))

(defn add-layer-to-bottom [layer]
  (let [new-layers (into [] (concat [layer] @layers))]
    (println new-layers)
    (reset! layers new-layers)))

(defn add-layer-to-top [layer]
  (let [new-layers (conj @layers layer)]
    (println new-layers)
    (reset! layers new-layers)))

(defn add-layer [layer]
  (add-layer-to-top layer))

(defn update-layers []
  (dorun (for [layer @layers] (update-layer layer))))

(defn draw-layers []
  (dorun (for [layer @layers] (draw-layer layer))))
