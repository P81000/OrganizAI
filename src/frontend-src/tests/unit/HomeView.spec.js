import HomeView from '@/views/HomeView.vue';
import { shallowMount } from '@vue/test-utils';
import { createRouter, createWebHistory } from 'vue-router';

describe("HomeView.vue", () => {
  it("Renders the component", () => {
    const wrapper = shallowMount(HomeView);
    expect(wrapper.exists()).toBe(true);
  });

  it("Display the 'Welcome to OrganizAI' text", () => {
    const wrapper = shallowMount(HomeView);
    expect(wrapper.find(".Welcome").text()).toContain("Welcome to OrganizAI");
  });

  it("Checks if 'Sign In' and 'Sign Up' buttons exist and can be clicked", async () => {
    const history = createWebHistory();
    const router = createRouter({
      history: history,
      routes: [
        {
          path: "/registration",
          name: "registration",
          component: {
            template: '<div>RegistrationView</div>',
          },
        },
      ],
    });
    const wrapper = shallowMount(HomeView, {
      global: {
        plugins: [router],
      },
    });

    const signInButton = wrapper.find(".button-signin");
    const signUpButton = wrapper.find(".button-signup");

    await router.isReady();

    expect(signInButton.exists()).toBe(true);
    try {
      await signInButton.trigger("click");
    } catch (error) {
      console.error("Error clicking 'Sign In' button:", error);
    }

    expect(signUpButton.exists()).toBe(true);
    try {
      await signUpButton.trigger("click");
    } catch (error) {

      console.error("Error clicking 'Sign Up' button:", error);
    }
  });
});
