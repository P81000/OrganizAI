import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import RegistrationView from "../views/RegistrationView.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },
  {
    path: "/registration",
    name: "registration",
    component: RegistrationView,
  },
  {
    path: "/main-page",
    name: "main-page",
    component: () =>
      import(/* webpackChunkName: "main-page" */ "../views/MainView.vue"),
  },
  {
    path: "/:pathMatch(.*)*",
    name: "not-found",
    component: () =>
      import(/* webpackChunkName: "not-found" */ "../views/NotFoundView.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
