(ns clojurewerkz.elephant.cards
  (:require [clojurewerkz.elephant.conversion :as cnv])
  (:import [clojure.lang IPersistentMap])
  (:refer-clojure :exclude [list update]))

;;
;; API
;;

(defn create
  [^IPersistentMap customer ^IPersistentMap card]
  (if-let [o (:__origin__ customer)]
    (cnv/card->map (.createCard o {"source" (merge card {"object" "card"})}))
    (throw (IllegalArgumentException.
            "cards/create only accepts maps returned by customer/create and other library functions that return customer maps"))))

(defn create-from-token
  [^IPersistentMap customer ^String token]
  (if-let [o (:__origin__ customer)]
    (cnv/card->map (.createCard o token))
    (throw (IllegalArgumentException.
            "cards/create only accepts maps returned by customer/create and other library functions that return customer maps"))))

(defn update
  [^IPersistentMap card ^IPersistentMap opts]
  (if-let [o (:__origin__ card)]
       (cnv/card->map (.update o opts))
       (throw (IllegalArgumentException.
               "cards/update only accepts maps returned by cards/create and other library functions that return card information"))))
