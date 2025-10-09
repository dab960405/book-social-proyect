// Karma configuration for Angular (optimized for local + CI)
module.exports = function (config) {
  config.set({
    // 🧩 Base path: tu carpeta de Angular (no modificar)
    basePath: '',

    // ⚙️ Frameworks de testing
    frameworks: ['jasmine', '@angular-devkit/build-angular'],

    // 📦 Plugins necesarios (incluye Chrome launcher para CI)
    plugins: [
      require('karma-jasmine'),
      require('karma-chrome-launcher'),
      require('karma-jasmine-html-reporter'),
      require('karma-coverage'),
      require('@angular-devkit/build-angular/plugins/karma'),
    ],

    // ⚙️ Configuración del cliente (para mostrar resultados)
    client: {
      clearContext: false, // deja visible el resultado en navegador local
    },

    // 📊 Reportes de progreso y coverage
    reporters: ['progress', 'kjhtml'],
    coverageReporter: {
      dir: require('path').join(__dirname, './coverage'),
      subdir: '.',
      reporters: [{ type: 'html' }, { type: 'text-summary' }],
    },

    // ⚙️ Config general del servidor
    port: 9876,
    colors: true,
    logLevel: config.LOG_INFO,

    // 🚀 En CI no se usa watch
    autoWatch: false,

    // ✅ NAVEGADOR DEFAULT
    browsers: ['ChromeHeadlessCI'],

    // 🧩 SINGLE RUN: hace que las pruebas se ejecuten una sola vez
    singleRun: true,

    // 🔄 Reejecuta si detecta cambios (solo local)
    restartOnFileChange: true,

    // 👇 Config especial para entorno sin interfaz (CI)
    customLaunchers: {
      ChromeHeadlessCI: {
        base: 'ChromeHeadless',
        flags: [
          '--headless',
          '--no-sandbox',
          '--disable-gpu',
          '--disable-dev-shm-usage',
          '--disable-software-rasterizer',
          '--remote-debugging-port=9222',
        ],
      },
    },
  });
};