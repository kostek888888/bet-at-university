// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  config: {
    baseURL: 'http://localhost:8080',
    manuLogoURL: '/assets/logo_white.png',
    sliders: {
      'sliderImg1': '../assets/slider1.png',
      'sliderImg2': '../assets/slider2.png',
      'sliderImg3': '../assets/slider3.png'
    }
  }
};
