import React, {Component} from 'react';
import { AppRegistry, Image, Text, View, ListView, StyleSheet } from 'react-native';


const rows = [
  'Singing',
  'Dancing',
  'Composing',
]

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 22
  },
  row: {
    padding: 15,
    marginBottom: 5,
    backgroundColor: 'limegreen',
  },
});

export class Bananas extends Component {
  render() {
    let pic = {
      uri: 'https://upload.wikimedia.org/wikipedia/commons/d/de/Bananavarieties.jpg'
    }
    return (
      <Image source={pic} style={
    { width: 193, height: 110 }}/>
    );
  }
}

export class HobbyList extends Component{
   constructor() {
    super();
    const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
    this.state = {
      dataSource: ds.cloneWithRows(rows),
      dataArray: rows
    };
  }
   render() {
    return (
      <ListView
        style={styles.container}
        dataSource={this.state.dataSource}
        renderRow={(rowData) => <Text>ce pula mea{rowData}</Text>}
      />
    );
  }
}

export class Hobby extends Component{
  constructor(props){
      super(props)
  }
  render(){
  let name = this.props.name;
  let description = this.props.description;
  let hours = this.props.hours;
    return(
      <View style={styles}>
      <Text> {name} {description} {hours}</Text>
      </View>
    );
  }
}
export default class HelloWorldApp extends Component {
  render() {
    return (
      <View>
      <HobbyList/>
      <Hobby name='dancing' description='jigglebabe' hours='3'/>
      </View>
    );
  }
}

