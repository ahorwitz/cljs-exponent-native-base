(ns again.core
    (:require [reagent.core :as r :refer [atom]]
              [re-frame.core :refer [subscribe dispatch dispatch-sync]]
              [again.handlers]
              [again.subs]
              [cljs-exponent.reagent :refer [text view image touchable-highlight] :as rn]))

(defn get-comp [module component]
  (r/adapt-react-class (aget module component)))
  
(def native-base (js/require "native-base"))

(def Button (get-comp native-base "Button"))
(def Body (get-comp native-base "Body"))
(def Title (get-comp native-base "Title"))
(def Container (get-comp native-base "Container"))
(def Content (get-comp native-base "Content"))
(def Header (get-comp native-base "Header"))
(def Tabs (get-comp native-base "Tabs"))
(def Tab (get-comp native-base "Tab"))
(def Text (get-comp native-base "Text"))
(def Left (get-comp native-base "Left"))
(def Right (get-comp native-base "Right"))
(def Icon (get-comp native-base "Icon"))
(def Footer (get-comp native-base "Footer"))
(def FooterTab (get-comp native-base "FooterTab"))
  
(defn alert [title]
  (.alert rn/alert title))

(defn app-root []
  (let [greeting (subscribe [:get-greeting])]
    (fn []
      [Container
      [Header {:hasTabs true}
        [Left
          [Button {:transparent true :onPress #(alert "menu")}
            [Icon {:name "menu"}]]]
        [Body
          [Title "I'm gonna be up here!"]]
        [Right]]
      [Tabs
        [Tab {:heading "tab1"}
          [text "Fake content 1"]]
        [Tab {:heading "tab2"}
          [text "Fake content 2"]]]
      [Footer
        [FooterTab
         [Button
          [Icon {:name "paper"}]]
         [Button
          [Icon {:name "map"}]]
         [Button
          [Icon {:name "keypad"}]]]]])))

(defn init []
  (.registerComponent rn/app-registry "main" #(r/reactify-component app-root)))