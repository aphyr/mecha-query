(ns mecha-query.core
  (:import (mecha_query QueryProcess)))

(defn process 
  "Creates a new Process."
  ([fun pname args]
    (QueryProcess.
      (when fun (name fun))
      (when pname (name pname))
      args))
  ([fun name]
   (process fun name []))
  ([fun]
   (process fun nil [])))

(defn pipe [src dest]
  "Connect all src to all dest. src and dest may be Processes or sequences
  of processes."
  (let [src  (flatten [src])
        dest (flatten [dest])]
    (doseq [s src d dest]
      (.addChild s d)
      (.addParent d s))))
(def | pipe)

; Explicitly define a function with positional and optional keyword args.
(defn get 
  ([bucket key opts]
    (process :get nil [bucket key opts]))
  ([bucket key]
    (get bucket key {})))

; Inline keyword args
(defn warp [& opts]
  (process :warp nil [(apply hash-map opts)]))
