import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

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
    component: () =>
      import(/* webpackChunkName: "login" */ "../views/RegistrationView.vue"),
  },
  {
    path: "/calendario",
    name: "calendario",
    component: () =>
        import(/* webpackChunkName: "login" */ "../views/CalendarView.vue"),
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
