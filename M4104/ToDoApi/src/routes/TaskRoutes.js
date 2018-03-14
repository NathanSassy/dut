'use strict';
module.exports = function(app) {
  var taskController = require('../controllers/TaskController');

  app.route('/tasks')
    .get(taskController.getAllTasks)
    .post(taskController.addtask);

  app.route('/tasks/:taskId')
    .get(taskController.getTask)
    .put(taskController.updateTask)
    .delete(taskController.deleteTask);
};
